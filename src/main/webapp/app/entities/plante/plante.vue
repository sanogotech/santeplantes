<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('santeplantesApp.plante.home.title')" id="plante-heading">Plantes</span>
            <router-link :to="{name: 'PlanteCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-plante">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('santeplantesApp.plante.home.createLabel')">
                    Create a new Plante
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
        <div class="alert alert-warning" v-if="!isFetching && plantes && plantes.length === 0">
            <span v-text="$t('santeplantesApp.plante.home.notFound')">No plantes found</span>
        </div>
        <div class="table-responsive" v-if="plantes && plantes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('nomScientifique')"><span v-text="$t('santeplantesApp.plante.nomScientifique')">Nom Scientifique</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nomScientifique'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('nomCommun')"><span v-text="$t('santeplantesApp.plante.nomCommun')">Nom Commun</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nomCommun'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('famille')"><span v-text="$t('santeplantesApp.plante.famille')">Famille</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'famille'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('genre')"><span v-text="$t('santeplantesApp.plante.genre')">Genre</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'genre'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('maladie.nom')"><span v-text="$t('santeplantesApp.plante.maladie')">Maladie</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'maladie.nom'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('bienfait.nom')"><span v-text="$t('santeplantesApp.plante.bienfait')">Bienfait</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bienfait.nom'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="plante in plantes"
                    :key="plante.id">
                    <td>
                        <router-link :to="{name: 'PlanteView', params: {planteId: plante.id}}">{{plante.id}}</router-link>
                    </td>
                    <td>{{plante.nomScientifique}}</td>
                    <td>{{plante.nomCommun}}</td>
                    <td>{{plante.famille}}</td>
                    <td>{{plante.genre}}</td>
                    <td>
                        <div v-if="plante.maladie">
                            <router-link :to="{name: 'MaladieView', params: {maladieId: plante.maladie.id}}">{{plante.maladie.nom}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="plante.bienfait">
                            <router-link :to="{name: 'BienfaitView', params: {bienfaitId: plante.bienfait.id}}">{{plante.bienfait.nom}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PlanteView', params: {planteId: plante.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'PlanteEdit', params: {planteId: plante.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(plante)"
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
            <span slot="modal-title"><span id="santeplantesApp.plante.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-plante-heading" v-text="$t('santeplantesApp.plante.delete.question', {'id': removeId})">Are you sure you want to delete this Plante?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-plante" v-text="$t('entity.action.delete')" v-on:click="removePlante()">Delete</button>
            </div>
        </b-modal>
        <div v-show="plantes && plantes.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./plante.component.ts">
</script>
