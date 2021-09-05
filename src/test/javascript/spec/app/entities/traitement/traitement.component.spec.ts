/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import TraitementComponent from '@/entities/traitement/traitement.vue';
import TraitementClass from '@/entities/traitement/traitement.component';
import TraitementService from '@/entities/traitement/traitement.service';

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
  describe('Traitement Management Component', () => {
    let wrapper: Wrapper<TraitementClass>;
    let comp: TraitementClass;
    let traitementServiceStub: SinonStubbedInstance<TraitementService>;

    beforeEach(() => {
      traitementServiceStub = sinon.createStubInstance<TraitementService>(TraitementService);
      traitementServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TraitementClass>(TraitementComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          traitementService: () => traitementServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      traitementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTraitements();
      await comp.$nextTick();

      // THEN
      expect(traitementServiceStub.retrieve.called).toBeTruthy();
      expect(comp.traitements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      traitementServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeTraitement();
      await comp.$nextTick();

      // THEN
      expect(traitementServiceStub.delete.called).toBeTruthy();
      expect(traitementServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
