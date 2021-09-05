/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import TradipraticienComponentsPage, { TradipraticienDeleteDialog } from './tradipraticien.page-object';
import TradipraticienUpdatePage from './tradipraticien-update.page-object';
import TradipraticienDetailsPage from './tradipraticien-details.page-object';

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

describe('Tradipraticien e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: TradipraticienUpdatePage;
  let detailsPage: TradipraticienDetailsPage;
  let listPage: TradipraticienComponentsPage;
  let deleteDialog: TradipraticienDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Tradipraticiens', async () => {
    await navBarPage.getEntityPage('tradipraticien');
    listPage = new TradipraticienComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Tradipraticien page', async () => {
      await listPage.createButton.click();
      updatePage = new TradipraticienUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/santeplantesApp.tradipraticien.home.createOrEditLabel/);
    });

    it('should create and save Tradipraticiens', async () => {
      await updatePage.nomInput.sendKeys('nom');
      expect(await updatePage.nomInput.getAttribute('value')).to.match(/nom/);

      await updatePage.prenomInput.sendKeys('prenom');
      expect(await updatePage.prenomInput.getAttribute('value')).to.match(/prenom/);

      await updatePage.entrepriseInput.sendKeys('entreprise');
      expect(await updatePage.entrepriseInput.getAttribute('value')).to.match(/entreprise/);

      await updatePage.phoneInput.sendKeys('phone');
      expect(await updatePage.phoneInput.getAttribute('value')).to.match(/phone/);

      await updatePage.villeInput.sendKeys('ville');
      expect(await updatePage.villeInput.getAttribute('value')).to.match(/ville/);

      await updatePage.adresseInput.sendKeys('adresse');
      expect(await updatePage.adresseInput.getAttribute('value')).to.match(/adresse/);

      await selectLastOption(updatePage.fiabiliteSelect);

      const selectedRas = await updatePage.rasInput.isSelected();
      if (selectedRas) {
        await updatePage.rasInput.click();
        expect(await updatePage.rasInput.isSelected()).to.be.false;
      } else {
        await updatePage.rasInput.click();
        expect(await updatePage.rasInput.isSelected()).to.be.true;
      }

      // await  selectLastOption(updatePage.maladieSelect);

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

        deleteDialog = new TradipraticienDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/santeplantesApp.tradipraticien.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Tradipraticien page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.last());
        await click(detailsButton);

        detailsPage = new TradipraticienDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Tradipraticien page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.last());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.nomInput.clear();
        await updatePage.nomInput.sendKeys('modified');
        expect(await updatePage.nomInput.getAttribute('value')).to.match(/modified/);

        await updatePage.prenomInput.clear();
        await updatePage.prenomInput.sendKeys('modified');
        expect(await updatePage.prenomInput.getAttribute('value')).to.match(/modified/);

        await updatePage.entrepriseInput.clear();
        await updatePage.entrepriseInput.sendKeys('modified');
        expect(await updatePage.entrepriseInput.getAttribute('value')).to.match(/modified/);

        await updatePage.phoneInput.clear();
        await updatePage.phoneInput.sendKeys('modified');
        expect(await updatePage.phoneInput.getAttribute('value')).to.match(/modified/);

        await updatePage.villeInput.clear();
        await updatePage.villeInput.sendKeys('modified');
        expect(await updatePage.villeInput.getAttribute('value')).to.match(/modified/);

        await updatePage.adresseInput.clear();
        await updatePage.adresseInput.sendKeys('modified');
        expect(await updatePage.adresseInput.getAttribute('value')).to.match(/modified/);

        const selectedRas = await updatePage.rasInput.isSelected();
        if (selectedRas) {
          await updatePage.rasInput.click();
          expect(await updatePage.rasInput.isSelected()).to.be.false;
        } else {
          await updatePage.rasInput.click();
          expect(await updatePage.rasInput.isSelected()).to.be.true;
        }

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
