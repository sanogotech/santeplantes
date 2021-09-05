/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PlanteComponentsPage, { PlanteDeleteDialog } from './plante.page-object';
import PlanteUpdatePage from './plante-update.page-object';
import PlanteDetailsPage from './plante-details.page-object';

import {
  clear,
  click,
  getRecordsCount,
  isVisible,
  selectLastOption,
  waitUntilAllDisplayed,
  waitUntilAnyDisplayed,
  waitUntilCount,
  waitUntilDisplayed,
  waitUntilHidden,
} from '../../util/utils';

const expect = chai.expect;

describe('Plante e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: PlanteUpdatePage;
  let detailsPage: PlanteDetailsPage;
  let listPage: PlanteComponentsPage;
  let deleteDialog: PlanteDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Plantes', async () => {
    await navBarPage.getEntityPage('plante');
    listPage = new PlanteComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Plante page', async () => {
      await listPage.createButton.click();
      updatePage = new PlanteUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/santeplantesApp.plante.home.createOrEditLabel/);
    });

    it('should create and save Plantes', async () => {
      await updatePage.nomScientifiqueInput.sendKeys('nomScientifique');
      expect(await updatePage.nomScientifiqueInput.getAttribute('value')).to.match(/nomScientifique/);

      await updatePage.nomCommunInput.sendKeys('nomCommun');
      expect(await updatePage.nomCommunInput.getAttribute('value')).to.match(/nomCommun/);

      await updatePage.familleInput.sendKeys('famille');
      expect(await updatePage.familleInput.getAttribute('value')).to.match(/famille/);

      await updatePage.genreInput.sendKeys('genre');
      expect(await updatePage.genreInput.getAttribute('value')).to.match(/genre/);

      // await  selectLastOption(updatePage.maladieSelect);
      // await  selectLastOption(updatePage.bienfaitSelect);

      expect(await updatePage.saveButton.isEnabled()).to.be.true;
      await updatePage.saveButton.click();

      await waitUntilHidden(updatePage.saveButton);
      expect(await isVisible(updatePage.saveButton)).to.be.false;

      await waitUntilDisplayed(listPage.successAlert);
      expect(await listPage.successAlert.isDisplayed()).to.be.true;

      await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      expect(await listPage.records.count()).to.eq(beforeRecordsCount + 1);
    });

    describe('Details, Update, Delete flow', () => {
      after(async () => {
        const deleteButton = listPage.getDeleteButton(listPage.records.first());
        await click(deleteButton);

        deleteDialog = new PlanteDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/santeplantesApp.plante.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Plante page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new PlanteDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Plante page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.nomScientifiqueInput.clear();
        await updatePage.nomScientifiqueInput.sendKeys('modified');
        expect(await updatePage.nomScientifiqueInput.getAttribute('value')).to.match(/modified/);

        await updatePage.nomCommunInput.clear();
        await updatePage.nomCommunInput.sendKeys('modified');
        expect(await updatePage.nomCommunInput.getAttribute('value')).to.match(/modified/);

        await updatePage.familleInput.clear();
        await updatePage.familleInput.sendKeys('modified');
        expect(await updatePage.familleInput.getAttribute('value')).to.match(/modified/);

        await updatePage.genreInput.clear();
        await updatePage.genreInput.sendKeys('modified');
        expect(await updatePage.genreInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
