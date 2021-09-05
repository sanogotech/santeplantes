<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('santeplantesApp.tradipraticien.home.title')" id="tradipraticien-heading">Tradipraticiens</span>
            <router-link :to="{name: 'TradipraticienCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-tradipraticien">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('santeplantesApp.tradipraticien.home.createLabel')">
                    Create a new Tradipraticien
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
        <div class="alert alert-warning" v-if="!isFetching && tradipraticiens && tradipraticiens.length === 0">
            <span v-text="$t('santeplantesApp.tradipraticien.home.notFound')">No tradipraticiens found</span>
        </div>
        <div class="table-responsive" v-if="tradipraticiens && tradipraticiens.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.nom')">Nom</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.prenom')">Prenom</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.entreprise')">Entreprise</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.phone')">Phone</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.ville')">Ville</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.adresse')">Adresse</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.fiabilite')">Fiabilite</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.ras')">Ras</span></th>
                    <th><span v-text="$t('santeplantesApp.tradipraticien.maladie')">Maladie</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="tradipraticien in tradipraticiens"
                    :key="tradipraticien.id">
                    <td>
                        <router-link :to="{name: 'TradipraticienView', params: {tradipraticienId: tradipraticien.id}}">{{tradipraticien.id}}</router-link>
                    </td>
                    <td>{{tradipraticien.nom}}</td>
                    <td>{{tradipraticien.prenom}}</td>
                    <td>{{tradipraticien.entreprise}}</td>
                    <td>{{tradipraticien.phone}}</td>
                    <td>{{tradipraticien.ville}}</td>
                    <td>{{tradipraticien.adresse}}</td>
                    <td v-text="$t('santeplantesApp.Fiabilite.' + tradipraticien.fiabilite)">{{tradipraticien.fiabilite}}</td>
                    <td>{{tradipraticien.ras}}</td>
                    <td>
                        <div v-if="tradipraticien.maladie">
                            <router-link :to="{name: 'MaladieView', params: {maladieId: tradipraticien.maladie.id}}">{{tradipraticien.maladie.nom}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'TradipraticienView', params: {tradipraticienId: tradipraticien.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'TradipraticienEdit', params: {tradipraticienId: tradipraticien.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(tradipraticien)"
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
            <span slot="modal-title"><span id="santeplantesApp.tradipraticien.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-tradipraticien-heading" v-text="$t('santeplantesApp.tradipraticien.delete.question', {'id': removeId})">Are you sure you want to delete this Tradipraticien?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-tradipraticien" v-text="$t('entity.action.delete')" v-on:click="removeTradipraticien()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./tradipraticien.component.ts">
</script>
