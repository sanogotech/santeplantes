<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="santeplantesApp.traitement.home.createOrEditLabel" v-text="$t('santeplantesApp.traitement.home.createOrEditLabel')">Create or edit a Traitement</h2>
                <div>
                    <div class="form-group" v-if="traitement.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="traitement.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.nom')" for="traitement-nom">Nom</label>
                        <input type="text" class="form-control" name="nom" id="traitement-nom"
                            :class="{'valid': !$v.traitement.nom.$invalid, 'invalid': $v.traitement.nom.$invalid }" v-model="$v.traitement.nom.$model"  required/>
                        <div v-if="$v.traitement.nom.$anyDirty && $v.traitement.nom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.traitement.nom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.fiabilite')" for="traitement-fiabilite">Fiabilite</label>
                        <select class="form-control" name="fiabilite" :class="{'valid': !$v.traitement.fiabilite.$invalid, 'invalid': $v.traitement.fiabilite.$invalid }" v-model="$v.traitement.fiabilite.$model" id="traitement-fiabilite"  required>
                            <option value="HAUT" v-bind:label="$t('santeplantesApp.Fiabilite.HAUT')">HAUT</option>
                            <option value="MOYEN" v-bind:label="$t('santeplantesApp.Fiabilite.MOYEN')">MOYEN</option>
                            <option value="BAS" v-bind:label="$t('santeplantesApp.Fiabilite.BAS')">BAS</option>
                        </select>
                        <div v-if="$v.traitement.fiabilite.$anyDirty && $v.traitement.fiabilite.$invalid">
                            <small class="form-text text-danger" v-if="!$v.traitement.fiabilite.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.maladie')" for="traitement-maladie">Maladie</label>
                        <select class="form-control" id="traitement-maladie" name="maladie" v-model="traitement.maladie">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="traitement.maladie && maladieOption.id === traitement.maladie.id ? traitement.maladie : maladieOption" v-for="maladieOption in maladies" :key="maladieOption.id">{{maladieOption.nom}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.traitement.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./traitement-update.component.ts">
</script>
