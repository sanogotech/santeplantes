import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class TradipraticienUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('santeplantesApp.tradipraticien.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomInput: ElementFinder = element(by.css('input#tradipraticien-nom'));

  prenomInput: ElementFinder = element(by.css('input#tradipraticien-prenom'));

  entrepriseInput: ElementFinder = element(by.css('input#tradipraticien-entreprise'));

  phoneInput: ElementFinder = element(by.css('input#tradipraticien-phone'));

  villeInput: ElementFinder = element(by.css('input#tradipraticien-ville'));

  adresseInput: ElementFinder = element(by.css('input#tradipraticien-adresse'));

  fiabiliteSelect = element(by.css('select#tradipraticien-fiabilite'));

  rasInput: ElementFinder = element(by.css('input#tradipraticien-ras'));
  maladieSelect = element(by.css('select#tradipraticien-maladie'));
}
