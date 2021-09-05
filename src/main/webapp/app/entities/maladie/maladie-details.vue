<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="maladie">
                <h2 class="jh-entity-heading"><span v-text="$t('santeplantesApp.maladie.detail.title')">Maladie</span> {{maladie.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('santeplantesApp.maladie.nom')">Nom</span>
                    </dt>
                    <dd>
                        <span>{{maladie.nom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.maladie.type')">Type</span>
                    </dt>
                    <dd>
                        <span v-text="$t('santeplantesApp.TypeMaladie.' + maladie.type)">{{maladie.type}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.maladie.plante')">Plante</span>
                    </dt>
                    <dd>
                        <div v-if="maladie.plante">
                            <router-link :to="{name: 'PlanteView', params: {planteId: maladie.plante.id}}">{{maladie.plante.nom}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.maladie.tradipraticien')">Tradipraticien</span>
                    </dt>
                    <dd>
                        <div v-if="maladie.tradipraticien">
                            <router-link :to="{name: 'TradipraticienView', params: {tradipraticienId: maladie.tradipraticien.id}}">{{maladie.tradipraticien.nom}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.maladie.traitement')">Traitement</span>
                    </dt>
                    <dd>
                        <div v-if="maladie.traitement">
                            <router-link :to="{name: 'TraitementView', params: {traitementId: maladie.traitement.id}}">{{maladie.traitement.nom}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="maladie.id" :to="{name: 'MaladieEdit', params: {maladieId: maladie.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./maladie-details.component.ts">
</script>
