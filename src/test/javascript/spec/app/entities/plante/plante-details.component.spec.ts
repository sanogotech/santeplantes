/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PlanteDetailComponent from '@/entities/plante/plante-details.vue';
import PlanteClass from '@/entities/plante/plante-details.component';
import PlanteService from '@/entities/plante/plante.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Plante Management Detail Component', () => {
    let wrapper: Wrapper<PlanteClass>;
    let comp: PlanteClass;
    let planteServiceStub: SinonStubbedInstance<PlanteService>;

    beforeEach(() => {
      planteServiceStub = sinon.createStubInstance<PlanteService>(PlanteService);

      wrapper = shallowMount<PlanteClass>(PlanteDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { planteService: () => planteServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPlante = { id: 123 };
        planteServiceStub.find.resolves(foundPlante);

        // WHEN
        comp.retrievePlante(123);
        await comp.$nextTick();

        // THEN
        expect(comp.plante).toBe(foundPlante);
      });
    });
  });
});
