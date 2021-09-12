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
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.photo')" for="plante-photo">Photo</label>
                        <div>
                            <img v-bind:src="'data:' + plante.photoContentType + ';base64,' + plante.photo" style="max-height: 100px;" v-if="plante.photo" alt="plante image"/>
                            <div v-if="plante.photo" class="form-text text-danger clearfix">
                                <span class="pull-left">{{plante.photoContentType}}, {{byteSize(plante.photo)}}</span>
                                <button type="button" v-on:click="clearInputImage('photo', 'photoContentType', 'file_photo')" class="btn btn-secondary btn-xs pull-right">
                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                </button>
                            </div>
                            <input type="file" ref="file_photo" id="file_photo" v-on:change="setFileData($event, plante, 'photo', true)" accept="image/*" v-text="$t('entity.action.addimage')"/>
                        </div>
                        <input type="hidden" class="form-control" name="photo" id="plante-photo"
                            :class="{'valid': !$v.plante.photo.$invalid, 'invalid': $v.plante.photo.$invalid }" v-model="$v.plante.photo.$model" />
                        <input type="hidden" class="form-control" name="photoContentType" id="plante-photoContentType"
                            v-model="plante.photoContentType" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.bienfaits')" for="plante-bienfaits">Bienfaits</label>
                        <input type="text" class="form-control" name="bienfaits" id="plante-bienfaits"
                            :class="{'valid': !$v.plante.bienfaits.$invalid, 'invalid': $v.plante.bienfaits.$invalid }" v-model="$v.plante.bienfaits.$model"  required/>
                        <div v-if="$v.plante.bienfaits.$anyDirty && $v.plante.bienfaits.$invalid">
                            <small class="form-text text-danger" v-if="!$v.plante.bienfaits.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.plante.bienfaits.maxLength" v-text="$t('entity.validation.maxlength', {max: 350})">
                                This field cannot be longer than 350 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.imageBienfaits')" for="plante-imageBienfaits">Image Bienfaits</label>
                        <div>
                            <img v-bind:src="'data:' + plante.imageBienfaitsContentType + ';base64,' + plante.imageBienfaits" style="max-height: 100px;" v-if="plante.imageBienfaits" alt="plante image"/>
                            <div v-if="plante.imageBienfaits" class="form-text text-danger clearfix">
                                <span class="pull-left">{{plante.imageBienfaitsContentType}}, {{byteSize(plante.imageBienfaits)}}</span>
                                <button type="button" v-on:click="clearInputImage('imageBienfaits', 'imageBienfaitsContentType', 'file_imageBienfaits')" class="btn btn-secondary btn-xs pull-right">
                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                </button>
                            </div>
                            <input type="file" ref="file_imageBienfaits" id="file_imageBienfaits" v-on:change="setFileData($event, plante, 'imageBienfaits', true)" accept="image/*" v-text="$t('entity.action.addimage')"/>
                        </div>
                        <input type="hidden" class="form-control" name="imageBienfaits" id="plante-imageBienfaits"
                            :class="{'valid': !$v.plante.imageBienfaits.$invalid, 'invalid': $v.plante.imageBienfaits.$invalid }" v-model="$v.plante.imageBienfaits.$model" />
                        <input type="hidden" class="form-control" name="imageBienfaitsContentType" id="plante-imageBienfaitsContentType"
                            v-model="plante.imageBienfaitsContentType" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.typeMaladies')" for="plante-typeMaladies">Type Maladies</label>
                        <input type="text" class="form-control" name="typeMaladies" id="plante-typeMaladies"
                            :class="{'valid': !$v.plante.typeMaladies.$invalid, 'invalid': $v.plante.typeMaladies.$invalid }" v-model="$v.plante.typeMaladies.$model"  required/>
                        <div v-if="$v.plante.typeMaladies.$anyDirty && $v.plante.typeMaladies.$invalid">
                            <small class="form-text text-danger" v-if="!$v.plante.typeMaladies.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.plante.typeMaladies.maxLength" v-text="$t('entity.validation.maxlength', {max: 300})">
                                This field cannot be longer than 300 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.plante.maladies')" for="plante-maladies">Maladies</label>
                        <input type="text" class="form-control" name="maladies" id="plante-maladies"
                            :class="{'valid': !$v.plante.maladies.$invalid, 'invalid': $v.plante.maladies.$invalid }" v-model="$v.plante.maladies.$model"  required/>
                        <div v-if="$v.plante.maladies.$anyDirty && $v.plante.maladies.$invalid">
                            <small class="form-text text-danger" v-if="!$v.plante.maladies.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.plante.maladies.maxLength" v-text="$t('entity.validation.maxlength', {max: 350})">
                                This field cannot be longer than 350 characters.
                            </small>
                        </div>
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
