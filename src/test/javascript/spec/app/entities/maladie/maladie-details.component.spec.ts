/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MaladieDetailComponent from '@/entities/maladie/maladie-details.vue';
import MaladieClass from '@/entities/maladie/maladie-details.component';
import MaladieService from '@/entities/maladie/maladie.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Maladie Management Detail Component', () => {
    let wrapper: Wrapper<MaladieClass>;
    let comp: MaladieClass;
    let maladieServiceStub: SinonStubbedInstance<MaladieService>;

    beforeEach(() => {
      maladieServiceStub = sinon.createStubInstance<MaladieService>(MaladieService);

      wrapper = shallowMount<MaladieClass>(MaladieDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { maladieService: () => maladieServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMaladie = { id: 123 };
        maladieServiceStub.find.resolves(foundMaladie);

        // WHEN
        comp.retrieveMaladie(123);
        await comp.$nextTick();

        // THEN
        expect(comp.maladie).toBe(foundMaladie);
      });
    });
  });
});
