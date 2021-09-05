<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('santeplantesApp.bienfait.home.title')" id="bienfait-heading">Bienfaits</span>
            <router-link :to="{name: 'BienfaitCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-bienfait">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('santeplantesApp.bienfait.home.createLabel')">
                    Create a new Bienfait
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
        <div class="alert alert-warning" v-if="!isFetching && bienfaits && bienfaits.length === 0">
            <span v-text="$t('santeplantesApp.bienfait.home.notFound')">No bienfaits found</span>
        </div>
        <div class="table-responsive" v-if="bienfaits && bienfaits.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('santeplantesApp.bienfait.nom')">Nom</span></th>
                    <th><span v-text="$t('santeplantesApp.bienfait.description')">Description</span></th>
                    <th><span v-text="$t('santeplantesApp.bienfait.vitamine')">Vitamine</span></th>
                    <th><span v-text="$t('santeplantesApp.bienfait.mineraux')">Mineraux</span></th>
                    <th><span v-text="$t('santeplantesApp.bienfait.plante')">Plante</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="bienfait in bienfaits"
                    :key="bienfait.id">
                    <td>
                        <router-link :to="{name: 'BienfaitView', params: {bienfaitId: bienfait.id}}">{{bienfait.id}}</router-link>
                    </td>
                    <td>{{bienfait.nom}}</td>
                    <td>{{bienfait.description}}</td>
                    <td>{{bienfait.vitamine}}</td>
                    <td>{{bienfait.mineraux}}</td>
                    <td>
                        <div v-if="bienfait.plante">
                            <router-link :to="{name: 'PlanteView', params: {planteId: bienfait.plante.id}}">{{bienfait.plante.nomCommun}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'BienfaitView', params: {bienfaitId: bienfait.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'BienfaitEdit', params: {bienfaitId: bienfait.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(bienfait)"
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
            <span slot="modal-title"><span id="santeplantesApp.bienfait.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-bienfait-heading" v-text="$t('santeplantesApp.bienfait.delete.question', {'id': removeId})">Are you sure you want to delete this Bienfait?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-bienfait" v-text="$t('entity.action.delete')" v-on:click="removeBienfait()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./bienfait.component.ts">
</script>
