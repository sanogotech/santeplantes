<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('santeplantesApp.maladie.home.title')" id="maladie-heading">Maladies</span>
            <router-link :to="{name: 'MaladieCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-maladie">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('santeplantesApp.maladie.home.createLabel')">
                    Create a new Maladie
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && maladies && maladies.length === 0">
            <span v-text="$t('santeplantesApp.maladie.home.notFound')">No maladies found</span>
        </div>
        <div class="table-responsive" v-if="maladies && maladies.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('nom')"><span v-text="$t('santeplantesApp.maladie.nom')">Nom</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nom'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('type')"><span v-text="$t('santeplantesApp.maladie.type')">Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'type'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('plante.nom')"><span v-text="$t('santeplantesApp.maladie.plante')">Plante</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'plante.nom'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('tradipraticien.nom')"><span v-text="$t('santeplantesApp.maladie.tradipraticien')">Tradipraticien</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tradipraticien.nom'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('traitement.nom')"><span v-text="$t('santeplantesApp.maladie.traitement')">Traitement</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'traitement.nom'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="maladie in maladies"
                    :key="maladie.id">
                    <td>
                        <router-link :to="{name: 'MaladieView', params: {maladieId: maladie.id}}">{{maladie.id}}</router-link>
                    </td>
                    <td>{{maladie.nom}}</td>
                    <td v-text="$t('santeplantesApp.TypeMaladie.' + maladie.type)">{{maladie.type}}</td>
                    <td>
                        <div v-if="maladie.plante">
                            <router-link :to="{name: 'PlanteView', params: {planteId: maladie.plante.id}}">{{maladie.plante.nom}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="maladie.tradipraticien">
                            <router-link :to="{name: 'TradipraticienView', params: {tradipraticienId: maladie.tradipraticien.id}}">{{maladie.tradipraticien.nom}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="maladie.traitement">
                            <router-link :to="{name: 'TraitementView', params: {traitementId: maladie.traitement.id}}">{{maladie.traitement.nom}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MaladieView', params: {maladieId: maladie.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'MaladieEdit', params: {maladieId: maladie.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(maladie)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="santeplantesApp.maladie.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-maladie-heading" v-text="$t('santeplantesApp.maladie.delete.question', {'id': removeId})">Are you sure you want to delete this Maladie?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-maladie" v-text="$t('entity.action.delete')" v-on:click="removeMaladie()">Delete</button>
            </div>
        </b-modal>
        <div v-show="maladies && maladies.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./maladie.component.ts">
</script>
