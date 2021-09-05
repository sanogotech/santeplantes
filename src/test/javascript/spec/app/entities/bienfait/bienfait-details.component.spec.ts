/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import BienfaitDetailComponent from '@/entities/bienfait/bienfait-details.vue';
import BienfaitClass from '@/entities/bienfait/bienfait-details.component';
import BienfaitService from '@/entities/bienfait/bienfait.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Bienfait Management Detail Component', () => {
    let wrapper: Wrapper<BienfaitClass>;
    let comp: BienfaitClass;
    let bienfaitServiceStub: SinonStubbedInstance<BienfaitService>;

    beforeEach(() => {
      bienfaitServiceStub = sinon.createStubInstance<BienfaitService>(BienfaitService);

      wrapper = shallowMount<BienfaitClass>(BienfaitDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { bienfaitService: () => bienfaitServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBienfait = { id: 123 };
        bienfaitServiceStub.find.resolves(foundBienfait);

        // WHEN
        comp.retrieveBienfait(123);
        await comp.$nextTick();

        // THEN
        expect(comp.bienfait).toBe(foundBienfait);
      });
    });
  });
});
