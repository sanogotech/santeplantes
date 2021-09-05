<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="tradipraticien">
                <h2 class="jh-entity-heading"><span v-text="$t('santeplantesApp.tradipraticien.detail.title')">Tradipraticien</span> {{tradipraticien.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.nom')">Nom</span>
                    </dt>
                    <dd>
                        <span>{{tradipraticien.nom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.prenom')">Prenom</span>
                    </dt>
                    <dd>
                        <span>{{tradipraticien.prenom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.entreprise')">Entreprise</span>
                    </dt>
                    <dd>
                        <span>{{tradipraticien.entreprise}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.phone')">Phone</span>
                    </dt>
                    <dd>
                        <span>{{tradipraticien.phone}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.ville')">Ville</span>
                    </dt>
                    <dd>
                        <span>{{tradipraticien.ville}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.adresse')">Adresse</span>
                    </dt>
                    <dd>
                        <span>{{tradipraticien.adresse}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.fiabilite')">Fiabilite</span>
                    </dt>
                    <dd>
                        <span v-text="$t('santeplantesApp.Fiabilite.' + tradipraticien.fiabilite)">{{tradipraticien.fiabilite}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.ras')">Ras</span>
                    </dt>
                    <dd>
                        <span>{{tradipraticien.ras}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('santeplantesApp.tradipraticien.maladie')">Maladie</span>
                    </dt>
                    <dd>
                        <div v-if="tradipraticien.maladie">
                            <router-link :to="{name: 'MaladieView', params: {maladieId: tradipraticien.maladie.id}}">{{tradipraticien.maladie.nom}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="tradipraticien.id" :to="{name: 'TradipraticienEdit', params: {tradipraticienId: tradipraticien.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./tradipraticien-details.component.ts">
</script>
