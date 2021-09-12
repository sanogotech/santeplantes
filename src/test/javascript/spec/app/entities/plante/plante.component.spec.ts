/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import PlanteComponent from '@/entities/plante/plante.vue';
import PlanteClass from '@/entities/plante/plante.component';
import PlanteService from '@/entities/plante/plante.service';

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
  describe('Plante Management Component', () => {
    let wrapper: Wrapper<PlanteClass>;
    let comp: PlanteClass;
    let planteServiceStub: SinonStubbedInstance<PlanteService>;

    beforeEach(() => {
      planteServiceStub = sinon.createStubInstance<PlanteService>(PlanteService);
      planteServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<PlanteClass>(PlanteComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          planteService: () => planteServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      planteServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllPlantes();
      await comp.$nextTick();

      // THEN
      expect(planteServiceStub.retrieve.called).toBeTruthy();
      expect(comp.plantes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      planteServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removePlante();
      await comp.$nextTick();

      // THEN
      expect(planteServiceStub.delete.called).toBeTruthy();
      expect(planteServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
