/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import BienfaitUpdateComponent from '@/entities/bienfait/bienfait-update.vue';
import BienfaitClass from '@/entities/bienfait/bienfait-update.component';
import BienfaitService from '@/entities/bienfait/bienfait.service';

import PlanteService from '@/entities/plante/plante.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Bienfait Management Update Component', () => {
    let wrapper: Wrapper<BienfaitClass>;
    let comp: BienfaitClass;
    let bienfaitServiceStub: SinonStubbedInstance<BienfaitService>;

    beforeEach(() => {
      bienfaitServiceStub = sinon.createStubInstance<BienfaitService>(BienfaitService);

      wrapper = shallowMount<BienfaitClass>(BienfaitUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          bienfaitService: () => bienfaitServiceStub,

          planteService: () => new PlanteService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.bienfait = entity;
        bienfaitServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bienfaitServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.bienfait = entity;
        bienfaitServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bienfaitServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
