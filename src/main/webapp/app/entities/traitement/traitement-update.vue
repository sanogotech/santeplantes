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
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.typeExtraction')" for="traitement-typeExtraction">Type Extraction</label>
                        <select class="form-control" name="typeExtraction" :class="{'valid': !$v.traitement.typeExtraction.$invalid, 'invalid': $v.traitement.typeExtraction.$invalid }" v-model="$v.traitement.typeExtraction.$model" id="traitement-typeExtraction"  required>
                            <option value="Infusion" v-bind:label="$t('santeplantesApp.TypeExtraction.Infusion')">Infusion</option>
                            <option value="Decoction" v-bind:label="$t('santeplantesApp.TypeExtraction.Decoction')">Decoction</option>
                            <option value="Masseration" v-bind:label="$t('santeplantesApp.TypeExtraction.Masseration')">Masseration</option>
                            <option value="Autre" v-bind:label="$t('santeplantesApp.TypeExtraction.Autre')">Autre</option>
                        </select>
                        <div v-if="$v.traitement.typeExtraction.$anyDirty && $v.traitement.typeExtraction.$invalid">
                            <small class="form-text text-danger" v-if="!$v.traitement.typeExtraction.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.mixtureEtposologie')" for="traitement-mixtureEtposologie">Mixture Etposologie</label>
                        <textarea class="form-control" name="mixtureEtposologie" id="traitement-mixtureEtposologie"
                            :class="{'valid': !$v.traitement.mixtureEtposologie.$invalid, 'invalid': $v.traitement.mixtureEtposologie.$invalid }" v-model="$v.traitement.mixtureEtposologie.$model"  required></textarea>
                        <div v-if="$v.traitement.mixtureEtposologie.$anyDirty && $v.traitement.mixtureEtposologie.$invalid">
                            <small class="form-text text-danger" v-if="!$v.traitement.mixtureEtposologie.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.sourceInfos')" for="traitement-sourceInfos">Source Infos</label>
                        <textarea class="form-control" name="sourceInfos" id="traitement-sourceInfos"
                            :class="{'valid': !$v.traitement.sourceInfos.$invalid, 'invalid': $v.traitement.sourceInfos.$invalid }" v-model="$v.traitement.sourceInfos.$model" ></textarea>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.typeTraitement')" for="traitement-typeTraitement">Type Traitement</label>
                        <select class="form-control" name="typeTraitement" :class="{'valid': !$v.traitement.typeTraitement.$invalid, 'invalid': $v.traitement.typeTraitement.$invalid }" v-model="$v.traitement.typeTraitement.$model" id="traitement-typeTraitement" >
                            <option value="Preventif" v-bind:label="$t('santeplantesApp.TypeTraitement.Preventif')">Preventif</option>
                            <option value="Curatif" v-bind:label="$t('santeplantesApp.TypeTraitement.Curatif')">Curatif</option>
                            <option value="PreventifCuratif" v-bind:label="$t('santeplantesApp.TypeTraitement.PreventifCuratif')">PreventifCuratif</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.traitement.plante')" for="traitement-plante">Plante</label>
                        <select class="form-control" id="traitement-plante" name="plante" v-model="traitement.plante">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="traitement.plante && planteOption.id === traitement.plante.id ? traitement.plante : planteOption" v-for="planteOption in plantes" :key="planteOption.id">{{planteOption.nomCommun}}</option>
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
