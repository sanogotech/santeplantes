import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

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
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
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
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
