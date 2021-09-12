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
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.nomScientifique')">Nom Scientifique</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.nomCommun')">Nom Commun</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.famille')">Famille</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.genre')">Genre</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.photo')">Photo</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.bienfaits')">Bienfaits</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.imageBienfaits')">Image Bienfaits</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.typeMaladies')">Type Maladies</span></th>
                    <th><span v-text="$t('santeplantesApp.plante.maladies')">Maladies</span></th>
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
                        <a v-if="plante.photo" v-on:click="openFile(plante.photoContentType, plante.photo)">
                            <img v-bind:src="'data:' + plante.photoContentType + ';base64,' + plante.photo" style="max-height: 30px;" alt="plante image"/>
                        </a>
                        <span v-if="plante.photo">{{plante.photoContentType}}, {{byteSize(plante.photo)}}</span>
                    </td>
                    <td>{{plante.bienfaits}}</td>
                    <td>
                        <a v-if="plante.imageBienfaits" v-on:click="openFile(plante.imageBienfaitsContentType, plante.imageBienfaits)">
                            <img v-bind:src="'data:' + plante.imageBienfaitsContentType + ';base64,' + plante.imageBienfaits" style="max-height: 30px;" alt="plante image"/>
                        </a>
                        <span v-if="plante.imageBienfaits">{{plante.imageBienfaitsContentType}}, {{byteSize(plante.imageBienfaits)}}</span>
                    </td>
                    <td>{{plante.typeMaladies}}</td>
                    <td>{{plante.maladies}}</td>
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
    </div>
</template>

<script lang="ts" src="./plante.component.ts">
</script>
