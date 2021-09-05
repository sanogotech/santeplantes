<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="santeplantesApp.tradipraticien.home.createOrEditLabel" v-text="$t('santeplantesApp.tradipraticien.home.createOrEditLabel')">Create or edit a Tradipraticien</h2>
                <div>
                    <div class="form-group" v-if="tradipraticien.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="tradipraticien.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.nom')" for="tradipraticien-nom">Nom</label>
                        <input type="text" class="form-control" name="nom" id="tradipraticien-nom"
                            :class="{'valid': !$v.tradipraticien.nom.$invalid, 'invalid': $v.tradipraticien.nom.$invalid }" v-model="$v.tradipraticien.nom.$model"  required/>
                        <div v-if="$v.tradipraticien.nom.$anyDirty && $v.tradipraticien.nom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tradipraticien.nom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.prenom')" for="tradipraticien-prenom">Prenom</label>
                        <input type="text" class="form-control" name="prenom" id="tradipraticien-prenom"
                            :class="{'valid': !$v.tradipraticien.prenom.$invalid, 'invalid': $v.tradipraticien.prenom.$invalid }" v-model="$v.tradipraticien.prenom.$model"  required/>
                        <div v-if="$v.tradipraticien.prenom.$anyDirty && $v.tradipraticien.prenom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tradipraticien.prenom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.entreprise')" for="tradipraticien-entreprise">Entreprise</label>
                        <input type="text" class="form-control" name="entreprise" id="tradipraticien-entreprise"
                            :class="{'valid': !$v.tradipraticien.entreprise.$invalid, 'invalid': $v.tradipraticien.entreprise.$invalid }" v-model="$v.tradipraticien.entreprise.$model"  required/>
                        <div v-if="$v.tradipraticien.entreprise.$anyDirty && $v.tradipraticien.entreprise.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tradipraticien.entreprise.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.phone')" for="tradipraticien-phone">Phone</label>
                        <input type="text" class="form-control" name="phone" id="tradipraticien-phone"
                            :class="{'valid': !$v.tradipraticien.phone.$invalid, 'invalid': $v.tradipraticien.phone.$invalid }" v-model="$v.tradipraticien.phone.$model"  required/>
                        <div v-if="$v.tradipraticien.phone.$anyDirty && $v.tradipraticien.phone.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tradipraticien.phone.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.ville')" for="tradipraticien-ville">Ville</label>
                        <input type="text" class="form-control" name="ville" id="tradipraticien-ville"
                            :class="{'valid': !$v.tradipraticien.ville.$invalid, 'invalid': $v.tradipraticien.ville.$invalid }" v-model="$v.tradipraticien.ville.$model"  required/>
                        <div v-if="$v.tradipraticien.ville.$anyDirty && $v.tradipraticien.ville.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tradipraticien.ville.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.adresse')" for="tradipraticien-adresse">Adresse</label>
                        <input type="text" class="form-control" name="adresse" id="tradipraticien-adresse"
                            :class="{'valid': !$v.tradipraticien.adresse.$invalid, 'invalid': $v.tradipraticien.adresse.$invalid }" v-model="$v.tradipraticien.adresse.$model"  required/>
                        <div v-if="$v.tradipraticien.adresse.$anyDirty && $v.tradipraticien.adresse.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tradipraticien.adresse.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.fiabilite')" for="tradipraticien-fiabilite">Fiabilite</label>
                        <select class="form-control" name="fiabilite" :class="{'valid': !$v.tradipraticien.fiabilite.$invalid, 'invalid': $v.tradipraticien.fiabilite.$invalid }" v-model="$v.tradipraticien.fiabilite.$model" id="tradipraticien-fiabilite"  required>
                            <option value="HAUT" v-bind:label="$t('santeplantesApp.Fiabilite.HAUT')">HAUT</option>
                            <option value="MOYEN" v-bind:label="$t('santeplantesApp.Fiabilite.MOYEN')">MOYEN</option>
                            <option value="BAS" v-bind:label="$t('santeplantesApp.Fiabilite.BAS')">BAS</option>
                        </select>
                        <div v-if="$v.tradipraticien.fiabilite.$anyDirty && $v.tradipraticien.fiabilite.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tradipraticien.fiabilite.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.ras')" for="tradipraticien-ras">Ras</label>
                        <input type="checkbox" class="form-check" name="ras" id="tradipraticien-ras"
                            :class="{'valid': !$v.tradipraticien.ras.$invalid, 'invalid': $v.tradipraticien.ras.$invalid }" v-model="$v.tradipraticien.ras.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('santeplantesApp.tradipraticien.maladie')" for="tradipraticien-maladie">Maladie</label>
                        <select class="form-control" id="tradipraticien-maladie" name="maladie" v-model="tradipraticien.maladie">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="tradipraticien.maladie && maladieOption.id === tradipraticien.maladie.id ? tradipraticien.maladie : maladieOption" v-for="maladieOption in maladies" :key="maladieOption.id">{{maladieOption.nom}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.tradipraticien.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./tradipraticien-update.component.ts">
</script>
