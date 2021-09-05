/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MaladieUpdateComponent from '@/entities/maladie/maladie-update.vue';
import MaladieClass from '@/entities/maladie/maladie-update.component';
import MaladieService from '@/entities/maladie/maladie.service';

import PlanteService from '@/entities/plante/plante.service';

import TradipraticienService from '@/entities/tradipraticien/tradipraticien.service';

import TraitementService from '@/entities/traitement/traitement.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Maladie Management Update Component', () => {
    let wrapper: Wrapper<MaladieClass>;
    let comp: MaladieClass;
    let maladieServiceStub: SinonStubbedInstance<MaladieService>;

    beforeEach(() => {
      maladieServiceStub = sinon.createStubInstance<MaladieService>(MaladieService);

      wrapper = shallowMount<MaladieClass>(MaladieUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          maladieService: () => maladieServiceStub,

          planteService: () => new PlanteService(),

          tradipraticienService: () => new TradipraticienService(),

          traitementService: () => new TraitementService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.maladie = entity;
        maladieServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(maladieServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.maladie = entity;
        maladieServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(maladieServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
