/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TraitementDetailComponent from '@/entities/traitement/traitement-details.vue';
import TraitementClass from '@/entities/traitement/traitement-details.component';
import TraitementService from '@/entities/traitement/traitement.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Traitement Management Detail Component', () => {
    let wrapper: Wrapper<TraitementClass>;
    let comp: TraitementClass;
    let traitementServiceStub: SinonStubbedInstance<TraitementService>;

    beforeEach(() => {
      traitementServiceStub = sinon.createStubInstance<TraitementService>(TraitementService);

      wrapper = shallowMount<TraitementClass>(TraitementDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { traitementService: () => traitementServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTraitement = { id: 123 };
        traitementServiceStub.find.resolves(foundTraitement);

        // WHEN
        comp.retrieveTraitement(123);
        await comp.$nextTick();

        // THEN
        expect(comp.traitement).toBe(foundTraitement);
      });
    });
  });
});
