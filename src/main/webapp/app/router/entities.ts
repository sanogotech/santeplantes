import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Tradipraticien = () => import('@/entities/tradipraticien/tradipraticien.vue');
// prettier-ignore
const TradipraticienUpdate = () => import('@/entities/tradipraticien/tradipraticien-update.vue');
// prettier-ignore
const TradipraticienDetails = () => import('@/entities/tradipraticien/tradipraticien-details.vue');
// prettier-ignore
const Maladie = () => import('@/entities/maladie/maladie.vue');
// prettier-ignore
const MaladieUpdate = () => import('@/entities/maladie/maladie-update.vue');
// prettier-ignore
const MaladieDetails = () => import('@/entities/maladie/maladie-details.vue');
// prettier-ignore
const Plante = () => import('@/entities/plante/plante.vue');
// prettier-ignore
const PlanteUpdate = () => import('@/entities/plante/plante-update.vue');
// prettier-ignore
const PlanteDetails = () => import('@/entities/plante/plante-details.vue');
// prettier-ignore
const Traitement = () => import('@/entities/traitement/traitement.vue');
// prettier-ignore
const TraitementUpdate = () => import('@/entities/traitement/traitement-update.vue');
// prettier-ignore
const TraitementDetails = () => import('@/entities/traitement/traitement-details.vue');
// prettier-ignore
const Bienfait = () => import('@/entities/bienfait/bienfait.vue');
// prettier-ignore
const BienfaitUpdate = () => import('@/entities/bienfait/bienfait-update.vue');
// prettier-ignore
const BienfaitDetails = () => import('@/entities/bienfait/bienfait-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/tradipraticien',
    name: 'Tradipraticien',
    component: Tradipraticien,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/new',
    name: 'TradipraticienCreate',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/edit',
    name: 'TradipraticienEdit',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/view',
    name: 'TradipraticienView',
    component: TradipraticienDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie',
    name: 'Maladie',
    component: Maladie,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/new',
    name: 'MaladieCreate',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/edit',
    name: 'MaladieEdit',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/view',
    name: 'MaladieView',
    component: MaladieDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante',
    name: 'Plante',
    component: Plante,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/new',
    name: 'PlanteCreate',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/edit',
    name: 'PlanteEdit',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/view',
    name: 'PlanteView',
    component: PlanteDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement',
    name: 'Traitement',
    component: Traitement,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/new',
    name: 'TraitementCreate',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/edit',
    name: 'TraitementEdit',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/view',
    name: 'TraitementView',
    component: TraitementDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait',
    name: 'Bienfait',
    component: Bienfait,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/new',
    name: 'BienfaitCreate',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/edit',
    name: 'BienfaitEdit',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/view',
    name: 'BienfaitView',
    component: BienfaitDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/tradipraticien',
    name: 'Tradipraticien',
    component: Tradipraticien,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/new',
    name: 'TradipraticienCreate',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/edit',
    name: 'TradipraticienEdit',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/view',
    name: 'TradipraticienView',
    component: TradipraticienDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie',
    name: 'Maladie',
    component: Maladie,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/new',
    name: 'MaladieCreate',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/edit',
    name: 'MaladieEdit',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/view',
    name: 'MaladieView',
    component: MaladieDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante',
    name: 'Plante',
    component: Plante,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/new',
    name: 'PlanteCreate',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/edit',
    name: 'PlanteEdit',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/view',
    name: 'PlanteView',
    component: PlanteDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement',
    name: 'Traitement',
    component: Traitement,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/new',
    name: 'TraitementCreate',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/edit',
    name: 'TraitementEdit',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/view',
    name: 'TraitementView',
    component: TraitementDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait',
    name: 'Bienfait',
    component: Bienfait,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/new',
    name: 'BienfaitCreate',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/edit',
    name: 'BienfaitEdit',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/view',
    name: 'BienfaitView',
    component: BienfaitDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/tradipraticien',
    name: 'Tradipraticien',
    component: Tradipraticien,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/new',
    name: 'TradipraticienCreate',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/edit',
    name: 'TradipraticienEdit',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/view',
    name: 'TradipraticienView',
    component: TradipraticienDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie',
    name: 'Maladie',
    component: Maladie,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/new',
    name: 'MaladieCreate',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/edit',
    name: 'MaladieEdit',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/view',
    name: 'MaladieView',
    component: MaladieDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante',
    name: 'Plante',
    component: Plante,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/new',
    name: 'PlanteCreate',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/edit',
    name: 'PlanteEdit',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/view',
    name: 'PlanteView',
    component: PlanteDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement',
    name: 'Traitement',
    component: Traitement,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/new',
    name: 'TraitementCreate',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/edit',
    name: 'TraitementEdit',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/view',
    name: 'TraitementView',
    component: TraitementDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait',
    name: 'Bienfait',
    component: Bienfait,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/new',
    name: 'BienfaitCreate',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/edit',
    name: 'BienfaitEdit',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/view',
    name: 'BienfaitView',
    component: BienfaitDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/tradipraticien',
    name: 'Tradipraticien',
    component: Tradipraticien,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/new',
    name: 'TradipraticienCreate',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/edit',
    name: 'TradipraticienEdit',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/view',
    name: 'TradipraticienView',
    component: TradipraticienDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie',
    name: 'Maladie',
    component: Maladie,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/new',
    name: 'MaladieCreate',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/edit',
    name: 'MaladieEdit',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/view',
    name: 'MaladieView',
    component: MaladieDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante',
    name: 'Plante',
    component: Plante,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/new',
    name: 'PlanteCreate',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/edit',
    name: 'PlanteEdit',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/view',
    name: 'PlanteView',
    component: PlanteDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement',
    name: 'Traitement',
    component: Traitement,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/new',
    name: 'TraitementCreate',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/edit',
    name: 'TraitementEdit',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/view',
    name: 'TraitementView',
    component: TraitementDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait',
    name: 'Bienfait',
    component: Bienfait,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/new',
    name: 'BienfaitCreate',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/edit',
    name: 'BienfaitEdit',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/view',
    name: 'BienfaitView',
    component: BienfaitDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/maladie',
    name: 'Maladie',
    component: Maladie,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/new',
    name: 'MaladieCreate',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/edit',
    name: 'MaladieEdit',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/view',
    name: 'MaladieView',
    component: MaladieDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante',
    name: 'Plante',
    component: Plante,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/new',
    name: 'PlanteCreate',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/edit',
    name: 'PlanteEdit',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/view',
    name: 'PlanteView',
    component: PlanteDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait',
    name: 'Bienfait',
    component: Bienfait,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/new',
    name: 'BienfaitCreate',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/edit',
    name: 'BienfaitEdit',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/view',
    name: 'BienfaitView',
    component: BienfaitDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/tradipraticien',
    name: 'Tradipraticien',
    component: Tradipraticien,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/new',
    name: 'TradipraticienCreate',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/edit',
    name: 'TradipraticienEdit',
    component: TradipraticienUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/tradipraticien/:tradipraticienId/view',
    name: 'TradipraticienView',
    component: TradipraticienDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie',
    name: 'Maladie',
    component: Maladie,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/new',
    name: 'MaladieCreate',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/edit',
    name: 'MaladieEdit',
    component: MaladieUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/maladie/:maladieId/view',
    name: 'MaladieView',
    component: MaladieDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante',
    name: 'Plante',
    component: Plante,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/new',
    name: 'PlanteCreate',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/edit',
    name: 'PlanteEdit',
    component: PlanteUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/plante/:planteId/view',
    name: 'PlanteView',
    component: PlanteDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement',
    name: 'Traitement',
    component: Traitement,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/new',
    name: 'TraitementCreate',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/edit',
    name: 'TraitementEdit',
    component: TraitementUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/traitement/:traitementId/view',
    name: 'TraitementView',
    component: TraitementDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait',
    name: 'Bienfait',
    component: Bienfait,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/new',
    name: 'BienfaitCreate',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/edit',
    name: 'BienfaitEdit',
    component: BienfaitUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/bienfait/:bienfaitId/view',
    name: 'BienfaitView',
    component: BienfaitDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
