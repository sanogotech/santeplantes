<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="traitement">
                <h2 class="jh-entity-heading"><span v-text="$t('santeplantesApp.traitement.detail.title')">Traitement</span> {{traitement.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('santeplantesApp.traitement.nom')">Nom</span>
                    </dt>
                    <dd>
                        <span>{{traitement.nom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.traitement.fiabilite')">Fiabilite</span>
                    </dt>
                    <dd>
                        <span v-text="$t('santeplantesApp.Fiabilite.' + traitement.fiabilite)">{{traitement.fiabilite}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.traitement.maladie')">Maladie</span>
                    </dt>
                    <dd>
                        <div v-if="traitement.maladie">
                            <router-link :to="{name: 'MaladieView', params: {maladieId: traitement.maladie.id}}">{{traitement.maladie.nom}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="traitement.id" :to="{name: 'TraitementEdit', params: {traitementId: traitement.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./traitement-details.component.ts">
</script>
