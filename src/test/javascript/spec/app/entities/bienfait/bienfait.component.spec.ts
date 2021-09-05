/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import BienfaitComponent from '@/entities/bienfait/bienfait.vue';
import BienfaitClass from '@/entities/bienfait/bienfait.component';
import BienfaitService from '@/entities/bienfait/bienfait.service';

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
  describe('Bienfait Management Component', () => {
    let wrapper: Wrapper<BienfaitClass>;
    let comp: BienfaitClass;
    let bienfaitServiceStub: SinonStubbedInstance<BienfaitService>;

    beforeEach(() => {
      bienfaitServiceStub = sinon.createStubInstance<BienfaitService>(BienfaitService);
      bienfaitServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<BienfaitClass>(BienfaitComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          bienfaitService: () => bienfaitServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      bienfaitServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllBienfaits();
      await comp.$nextTick();

      // THEN
      expect(bienfaitServiceStub.retrieve.called).toBeTruthy();
      expect(comp.bienfaits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      bienfaitServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeBienfait();
      await comp.$nextTick();

      // THEN
      expect(bienfaitServiceStub.delete.called).toBeTruthy();
      expect(bienfaitServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
