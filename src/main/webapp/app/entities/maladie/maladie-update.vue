<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="santeplantesApp.maladie.home.createOrEditLabel" v-text="$t('santeplantesApp.maladie.home.createOrEditLabel')">Create or edit a Maladie</h2>
                <div>
                    <div class="form-group" v-if="maladie.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="maladie.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.maladie.nom')" for="maladie-nom">Nom</label>
                        <input type="text" class="form-control" name="nom" id="maladie-nom"
                            :class="{'valid': !$v.maladie.nom.$invalid, 'invalid': $v.maladie.nom.$invalid }" v-model="$v.maladie.nom.$model"  required/>
                        <div v-if="$v.maladie.nom.$anyDirty && $v.maladie.nom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.maladie.nom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.maladie.type')" for="maladie-type">Type</label>
                        <select class="form-control" name="type" :class="{'valid': !$v.maladie.type.$invalid, 'invalid': $v.maladie.type.$invalid }" v-model="$v.maladie.type.$model" id="maladie-type"  required>
                            <option value="Cardiovasculaire" v-bind:label="$t('santeplantesApp.TypeMaladie.Cardiovasculaire')">Cardiovasculaire</option>
                            <option value="Cancers" v-bind:label="$t('santeplantesApp.TypeMaladie.Cancers')">Cancers</option>
                            <option value="Systemenerveux" v-bind:label="$t('santeplantesApp.TypeMaladie.Systemenerveux')">Systemenerveux</option>
                            <option value="Cerveau" v-bind:label="$t('santeplantesApp.TypeMaladie.Cerveau')">Cerveau</option>
                            <option value="Respiratoires" v-bind:label="$t('santeplantesApp.TypeMaladie.Respiratoires')">Respiratoires</option>
                            <option value="IntimeSexe" v-bind:label="$t('santeplantesApp.TypeMaladie.IntimeSexe')">IntimeSexe</option>
                            <option value="Peau" v-bind:label="$t('santeplantesApp.TypeMaladie.Peau')">Peau</option>
                            <option value="Digestif" v-bind:label="$t('santeplantesApp.TypeMaladie.Digestif')">Digestif</option>
                            <option value="OsArticulation" v-bind:label="$t('santeplantesApp.TypeMaladie.OsArticulation')">OsArticulation</option>
                        </select>
                        <div v-if="$v.maladie.type.$anyDirty && $v.maladie.type.$invalid">
                            <small class="form-text text-danger" v-if="!$v.maladie.type.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.maladie.plante')" for="maladie-plante">Plante</label>
                        <select class="form-control" id="maladie-plante" name="plante" v-model="maladie.plante">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="maladie.plante && planteOption.id === maladie.plante.id ? maladie.plante : planteOption" v-for="planteOption in plantes" :key="planteOption.id">{{planteOption.nom}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.maladie.tradipraticien')" for="maladie-tradipraticien">Tradipraticien</label>
                        <select class="form-control" id="maladie-tradipraticien" name="tradipraticien" v-model="maladie.tradipraticien">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="maladie.tradipraticien && tradipraticienOption.id === maladie.tradipraticien.id ? maladie.tradipraticien : tradipraticienOption" v-for="tradipraticienOption in tradipraticiens" :key="tradipraticienOption.id">{{tradipraticienOption.nom}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.maladie.traitement')" for="maladie-traitement">Traitement</label>
                        <select class="form-control" id="maladie-traitement" name="traitement" v-model="maladie.traitement">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="maladie.traitement && traitementOption.id === maladie.traitement.id ? maladie.traitement : traitementOption" v-for="traitementOption in traitements" :key="traitementOption.id">{{traitementOption.nom}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.maladie.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./maladie-update.component.ts">
</script>
