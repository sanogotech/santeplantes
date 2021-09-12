/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TraitementUpdateComponent from '@/entities/traitement/traitement-update.vue';
import TraitementClass from '@/entities/traitement/traitement-update.component';
import TraitementService from '@/entities/traitement/traitement.service';

import PlanteService from '@/entities/plante/plante.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Traitement Management Update Component', () => {
    let wrapper: Wrapper<TraitementClass>;
    let comp: TraitementClass;
    let traitementServiceStub: SinonStubbedInstance<TraitementService>;

    beforeEach(() => {
      traitementServiceStub = sinon.createStubInstance<TraitementService>(TraitementService);

      wrapper = shallowMount<TraitementClass>(TraitementUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          traitementService: () => traitementServiceStub,

          planteService: () => new PlanteService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.traitement = entity;
        traitementServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(traitementServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.traitement = entity;
        traitementServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(traitementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
