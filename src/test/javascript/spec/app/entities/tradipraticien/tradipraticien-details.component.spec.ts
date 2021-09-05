/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TradipraticienDetailComponent from '@/entities/tradipraticien/tradipraticien-details.vue';
import TradipraticienClass from '@/entities/tradipraticien/tradipraticien-details.component';
import TradipraticienService from '@/entities/tradipraticien/tradipraticien.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Tradipraticien Management Detail Component', () => {
    let wrapper: Wrapper<TradipraticienClass>;
    let comp: TradipraticienClass;
    let tradipraticienServiceStub: SinonStubbedInstance<TradipraticienService>;

    beforeEach(() => {
      tradipraticienServiceStub = sinon.createStubInstance<TradipraticienService>(TradipraticienService);

      wrapper = shallowMount<TradipraticienClass>(TradipraticienDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { tradipraticienService: () => tradipraticienServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTradipraticien = { id: 123 };
        tradipraticienServiceStub.find.resolves(foundTradipraticien);

        // WHEN
        comp.retrieveTradipraticien(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tradipraticien).toBe(foundTradipraticien);
      });
    });
  });
});
