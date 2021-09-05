<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="santeplantesApp.plante.home.createOrEditLabel" v-text="$t('santeplantesApp.plante.home.createOrEditLabel')">Create or edit a Plante</h2>
                <div>
                    <div class="form-group" v-if="plante.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="plante.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.nomScientifique')" for="plante-nomScientifique">Nom Scientifique</label>
                        <input type="text" class="form-control" name="nomScientifique" id="plante-nomScientifique"
                            :class="{'valid': !$v.plante.nomScientifique.$invalid, 'invalid': $v.plante.nomScientifique.$invalid }" v-model="$v.plante.nomScientifique.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.nomCommun')" for="plante-nomCommun">Nom Commun</label>
                        <input type="text" class="form-control" name="nomCommun" id="plante-nomCommun"
                            :class="{'valid': !$v.plante.nomCommun.$invalid, 'invalid': $v.plante.nomCommun.$invalid }" v-model="$v.plante.nomCommun.$model"  required/>
                        <div v-if="$v.plante.nomCommun.$anyDirty && $v.plante.nomCommun.$invalid">
                            <small class="form-text text-danger" v-if="!$v.plante.nomCommun.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.famille')" for="plante-famille">Famille</label>
                        <input type="text" class="form-control" name="famille" id="plante-famille"
                            :class="{'valid': !$v.plante.famille.$invalid, 'invalid': $v.plante.famille.$invalid }" v-model="$v.plante.famille.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.genre')" for="plante-genre">Genre</label>
                        <input type="text" class="form-control" name="genre" id="plante-genre"
                            :class="{'valid': !$v.plante.genre.$invalid, 'invalid': $v.plante.genre.$invalid }" v-model="$v.plante.genre.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.maladie')" for="plante-maladie">Maladie</label>
                        <select class="form-control" id="plante-maladie" name="maladie" v-model="plante.maladie">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="plante.maladie && maladieOption.id === plante.maladie.id ? plante.maladie : maladieOption" v-for="maladieOption in maladies" :key="maladieOption.id">{{maladieOption.nom}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.bienfait')" for="plante-bienfait">Bienfait</label>
                        <select class="form-control" id="plante-bienfait" name="bienfait" v-model="plante.bienfait">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="plante.bienfait && bienfaitOption.id === plante.bienfait.id ? plante.bienfait : bienfaitOption" v-for="bienfaitOption in bienfaits" :key="bienfaitOption.id">{{bienfaitOption.nom}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.plante.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./plante-update.component.ts">
</script>
