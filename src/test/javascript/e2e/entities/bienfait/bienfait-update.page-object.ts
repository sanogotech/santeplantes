import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class BienfaitUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('santeplantesApp.bienfait.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomInput: ElementFinder = element(by.css('input#bienfait-nom'));

  descriptionInput: ElementFinder = element(by.css('input#bienfait-description'));

  vitamineInput: ElementFinder = element(by.css('input#bienfait-vitamine'));

  minerauxInput: ElementFinder = element(by.css('input#bienfait-mineraux'));

  planteSelect = element(by.css('select#bienfait-plante'));
}
