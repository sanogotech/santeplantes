/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import BienfaitComponentsPage, { BienfaitDeleteDialog } from './bienfait.page-object';
import BienfaitUpdatePage from './bienfait-update.page-object';
import BienfaitDetailsPage from './bienfait-details.page-object';

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

describe('Bienfait e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: BienfaitUpdatePage;
  let detailsPage: BienfaitDetailsPage;
  let listPage: BienfaitComponentsPage;
  let deleteDialog: BienfaitDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Bienfaits', async () => {
    await navBarPage.getEntityPage('bienfait');
    listPage = new BienfaitComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Bienfait page', async () => {
      await listPage.createButton.click();
      updatePage = new BienfaitUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/santeplantesApp.bienfait.home.createOrEditLabel/);
    });

    it('should create and save Bienfaits', async () => {
      await updatePage.nomInput.sendKeys('nom');
      expect(await updatePage.nomInput.getAttribute('value')).to.match(/nom/);

      await updatePage.descriptionInput.sendKeys('description');
      expect(await updatePage.descriptionInput.getAttribute('value')).to.match(/description/);

      await updatePage.vitamineInput.sendKeys('vitamine');
      expect(await updatePage.vitamineInput.getAttribute('value')).to.match(/vitamine/);

      await updatePage.minerauxInput.sendKeys('mineraux');
      expect(await updatePage.minerauxInput.getAttribute('value')).to.match(/mineraux/);

      // await  selectLastOption(updatePage.planteSelect);

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
        const deleteButton = listPage.getDeleteButton(listPage.records.last());
        await click(deleteButton);

        deleteDialog = new BienfaitDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/santeplantesApp.bienfait.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Bienfait page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.last());
        await click(detailsButton);

        detailsPage = new BienfaitDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Bienfait page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.last());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.nomInput.clear();
        await updatePage.nomInput.sendKeys('modified');
        expect(await updatePage.nomInput.getAttribute('value')).to.match(/modified/);

        await updatePage.descriptionInput.clear();
        await updatePage.descriptionInput.sendKeys('modified');
        expect(await updatePage.descriptionInput.getAttribute('value')).to.match(/modified/);

        await updatePage.vitamineInput.clear();
        await updatePage.vitamineInput.sendKeys('modified');
        expect(await updatePage.vitamineInput.getAttribute('value')).to.match(/modified/);

        await updatePage.minerauxInput.clear();
        await updatePage.minerauxInput.sendKeys('modified');
        expect(await updatePage.minerauxInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
