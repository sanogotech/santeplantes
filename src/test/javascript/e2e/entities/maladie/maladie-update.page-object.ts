import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class MaladieUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('santeplantesApp.maladie.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomInput: ElementFinder = element(by.css('input#maladie-nom'));

  typeSelect = element(by.css('select#maladie-type'));
  planteSelect = element(by.css('select#maladie-plante'));

  tradipraticienSelect = element(by.css('select#maladie-tradipraticien'));

  traitementSelect = element(by.css('select#maladie-traitement'));
}
