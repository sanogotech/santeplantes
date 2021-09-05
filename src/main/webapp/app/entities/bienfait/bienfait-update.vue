<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="santeplantesApp.bienfait.home.createOrEditLabel" v-text="$t('santeplantesApp.bienfait.home.createOrEditLabel')">Create or edit a Bienfait</h2>
                <div>
                    <div class="form-group" v-if="bienfait.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="bienfait.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.bienfait.nom')" for="bienfait-nom">Nom</label>
                        <input type="text" class="form-control" name="nom" id="bienfait-nom"
                            :class="{'valid': !$v.bienfait.nom.$invalid, 'invalid': $v.bienfait.nom.$invalid }" v-model="$v.bienfait.nom.$model"  required/>
                        <div v-if="$v.bienfait.nom.$anyDirty && $v.bienfait.nom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.bienfait.nom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.bienfait.description')" for="bienfait-description">Description</label>
                        <input type="text" class="form-control" name="description" id="bienfait-description"
                            :class="{'valid': !$v.bienfait.description.$invalid, 'invalid': $v.bienfait.description.$invalid }" v-model="$v.bienfait.description.$model"  required/>
                        <div v-if="$v.bienfait.description.$anyDirty && $v.bienfait.description.$invalid">
                            <small class="form-text text-danger" v-if="!$v.bienfait.description.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.bienfait.vitamine')" for="bienfait-vitamine">Vitamine</label>
                        <input type="text" class="form-control" name="vitamine" id="bienfait-vitamine"
                            :class="{'valid': !$v.bienfait.vitamine.$invalid, 'invalid': $v.bienfait.vitamine.$invalid }" v-model="$v.bienfait.vitamine.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.bienfait.mineraux')" for="bienfait-mineraux">Mineraux</label>
                        <input type="text" class="form-control" name="mineraux" id="bienfait-mineraux"
                            :class="{'valid': !$v.bienfait.mineraux.$invalid, 'invalid': $v.bienfait.mineraux.$invalid }" v-model="$v.bienfait.mineraux.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.bienfait.plante')" for="bienfait-plante">Plante</label>
                        <select class="form-control" id="bienfait-plante" name="plante" v-model="bienfait.plante">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="bienfait.plante && planteOption.id === bienfait.plante.id ? bienfait.plante : planteOption" v-for="planteOption in plantes" :key="planteOption.id">{{planteOption.nomCommun}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.bienfait.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./bienfait-update.component.ts">
</script>
