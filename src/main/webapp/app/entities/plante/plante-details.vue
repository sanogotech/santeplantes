<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="plante">
                <h2 class="jh-entity-heading"><span v-text="$t('santeplantesApp.plante.detail.title')">Plante</span> {{plante.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.nomScientifique')">Nom Scientifique</span>
                    </dt>
                    <dd>
                        <span>{{plante.nomScientifique}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.nomCommun')">Nom Commun</span>
                    </dt>
                    <dd>
                        <span>{{plante.nomCommun}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.famille')">Famille</span>
                    </dt>
                    <dd>
                        <span>{{plante.famille}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.genre')">Genre</span>
                    </dt>
                    <dd>
                        <span>{{plante.genre}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.photo')">Photo</span>
                    </dt>
                    <dd>
                        <div v-if="plante.photo">
                            <a v-on:click="openFile(plante.photoContentType, plante.photo)">
                                <img v-bind:src="'data:' + plante.photoContentType + ';base64,' + plante.photo" style="max-width: 100%;" alt="plante image"/>
                            </a>
                            {{plante.photoContentType}}, {{byteSize(plante.photo)}}
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.bienfaits')">Bienfaits</span>
                    </dt>
                    <dd>
                        <span>{{plante.bienfaits}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.imageBienfaits')">Image Bienfaits</span>
                    </dt>
                    <dd>
                        <div v-if="plante.imageBienfaits">
                            <a v-on:click="openFile(plante.imageBienfaitsContentType, plante.imageBienfaits)">
                                <img v-bind:src="'data:' + plante.imageBienfaitsContentType + ';base64,' + plante.imageBienfaits" style="max-width: 100%;" alt="plante image"/>
                            </a>
                            {{plante.imageBienfaitsContentType}}, {{byteSize(plante.imageBienfaits)}}
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.typeMaladies')">Type Maladies</span>
                    </dt>
                    <dd>
                        <span>{{plante.typeMaladies}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.maladies')">Maladies</span>
                    </dt>
                    <dd>
                        <span>{{plante.maladies}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="plante.id" :to="{name: 'PlanteEdit', params: {planteId: plante.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./plante-details.component.ts">
</script>
