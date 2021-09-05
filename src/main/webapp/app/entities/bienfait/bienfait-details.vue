<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="bienfait">
                <h2 class="jh-entity-heading"><span v-text="$t('santeplantesApp.bienfait.detail.title')">Bienfait</span> {{bienfait.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('santeplantesApp.bienfait.nom')">Nom</span>
                    </dt>
                    <dd>
                        <span>{{bienfait.nom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.bienfait.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{bienfait.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.bienfait.vitamine')">Vitamine</span>
                    </dt>
                    <dd>
                        <span>{{bienfait.vitamine}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.bienfait.mineraux')">Mineraux</span>
                    </dt>
                    <dd>
                        <span>{{bienfait.mineraux}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.bienfait.plante')">Plante</span>
                    </dt>
                    <dd>
                        <div v-if="bienfait.plante">
                            <router-link :to="{name: 'PlanteView', params: {planteId: bienfait.plante.id}}">{{bienfait.plante.nomCommun}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="bienfait.id" :to="{name: 'BienfaitEdit', params: {bienfaitId: bienfait.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./bienfait-details.component.ts">
</script>
