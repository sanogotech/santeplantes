/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TradipraticienUpdateComponent from '@/entities/tradipraticien/tradipraticien-update.vue';
import TradipraticienClass from '@/entities/tradipraticien/tradipraticien-update.component';
import TradipraticienService from '@/entities/tradipraticien/tradipraticien.service';

import MaladieService from '@/entities/maladie/maladie.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Tradipraticien Management Update Component', () => {
    let wrapper: Wrapper<TradipraticienClass>;
    let comp: TradipraticienClass;
    let tradipraticienServiceStub: SinonStubbedInstance<TradipraticienService>;

    beforeEach(() => {
      tradipraticienServiceStub = sinon.createStubInstance<TradipraticienService>(TradipraticienService);

      wrapper = shallowMount<TradipraticienClass>(TradipraticienUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          tradipraticienService: () => tradipraticienServiceStub,

          maladieService: () => new MaladieService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.tradipraticien = entity;
        tradipraticienServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tradipraticienServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.tradipraticien = entity;
        tradipraticienServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tradipraticienServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
