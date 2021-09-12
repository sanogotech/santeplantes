<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('santeplantesApp.traitement.home.title')" id="traitement-heading">Traitements</span>
            <router-link :to="{name: 'TraitementCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-traitement">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('santeplantesApp.traitement.home.createLabel')">
                    Create a new Traitement
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
        <div class="alert alert-warning" v-if="!isFetching && traitements && traitements.length === 0">
            <span v-text="$t('santeplantesApp.traitement.home.notFound')">No traitements found</span>
        </div>
        <div class="table-responsive" v-if="traitements && traitements.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('santeplantesApp.traitement.nom')">Nom</span></th>
                    <th><span v-text="$t('santeplantesApp.traitement.fiabilite')">Fiabilite</span></th>
                    <th><span v-text="$t('santeplantesApp.traitement.typeExtraction')">Type Extraction</span></th>
                    <th><span v-text="$t('santeplantesApp.traitement.mixtureEtposologie')">Mixture Etposologie</span></th>
                    <th><span v-text="$t('santeplantesApp.traitement.sourceInfos')">Source Infos</span></th>
                    <th><span v-text="$t('santeplantesApp.traitement.typeTraitement')">Type Traitement</span></th>
                    <th><span v-text="$t('santeplantesApp.traitement.plante')">Plante</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="traitement in traitements"
                    :key="traitement.id">
                    <td>
                        <router-link :to="{name: 'TraitementView', params: {traitementId: traitement.id}}">{{traitement.id}}</router-link>
                    </td>
                    <td>{{traitement.nom}}</td>
                    <td v-text="$t('santeplantesApp.Fiabilite.' + traitement.fiabilite)">{{traitement.fiabilite}}</td>
                    <td v-text="$t('santeplantesApp.TypeExtraction.' + traitement.typeExtraction)">{{traitement.typeExtraction}}</td>
                    <td>{{traitement.mixtureEtposologie}}</td>
                    <td>{{traitement.sourceInfos}}</td>
                    <td v-text="$t('santeplantesApp.TypeTraitement.' + traitement.typeTraitement)">{{traitement.typeTraitement}}</td>
                    <td>
                        <div v-if="traitement.plante">
                            <router-link :to="{name: 'PlanteView', params: {planteId: traitement.plante.id}}">{{traitement.plante.nomCommun}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'TraitementView', params: {traitementId: traitement.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'TraitementEdit', params: {traitementId: traitement.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(traitement)"
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
            <span slot="modal-title"><span id="santeplantesApp.traitement.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-traitement-heading" v-text="$t('santeplantesApp.traitement.delete.question', {'id': removeId})">Are you sure you want to delete this Traitement?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-traitement" v-text="$t('entity.action.delete')" v-on:click="removeTraitement()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./traitement.component.ts">
</script>
