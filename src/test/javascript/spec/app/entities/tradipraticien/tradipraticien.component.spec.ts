/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TradipraticienComponent from '@/entities/tradipraticien/tradipraticien.vue';
import TradipraticienClass from '@/entities/tradipraticien/tradipraticien.component';
import TradipraticienService from '@/entities/tradipraticien/tradipraticien.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Tradipraticien Management Component', () => {
    let wrapper: Wrapper<TradipraticienClass>;
    let comp: TradipraticienClass;
    let tradipraticienServiceStub: SinonStubbedInstance<TradipraticienService>;

    beforeEach(() => {
      tradipraticienServiceStub = sinon.createStubInstance<TradipraticienService>(TradipraticienService);
      tradipraticienServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TradipraticienClass>(TradipraticienComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          tradipraticienService: () => tradipraticienServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tradipraticienServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTradipraticiens();
      await comp.$nextTick();

      // THEN
      expect(tradipraticienServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tradipraticiens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tradipraticienServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeTradipraticien();
      await comp.$nextTick();

      // THEN
      expect(tradipraticienServiceStub.delete.called).toBeTruthy();
      expect(tradipraticienServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
