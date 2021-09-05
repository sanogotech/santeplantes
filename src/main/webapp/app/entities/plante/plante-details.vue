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
                        <span v-text="$t('santeplantesApp.plante.maladie')">Maladie</span>
                    </dt>
                    <dd>
                        <div v-if="plante.maladie">
                            <router-link :to="{name: 'MaladieView', params: {maladieId: plante.maladie.id}}">{{plante.maladie.nom}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.plante.bienfait')">Bienfait</span>
                    </dt>
                    <dd>
                        <div v-if="plante.bienfait">
                            <router-link :to="{name: 'BienfaitView', params: {bienfaitId: plante.bienfait.id}}">{{plante.bienfait.nom}}</router-link>
                        </div>
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
