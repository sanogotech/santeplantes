import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class TraitementUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('santeplantesApp.traitement.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomInput: ElementFinder = element(by.css('input#traitement-nom'));

  fiabiliteSelect = element(by.css('select#traitement-fiabilite'));

  typeExtractionSelect = element(by.css('select#traitement-typeExtraction'));

  mixtureEtposologieInput: ElementFinder = element(by.css('textarea#traitement-mixtureEtposologie'));

  sourceInfosInput: ElementFinder = element(by.css('textarea#traitement-sourceInfos'));

  typeTraitementSelect = element(by.css('select#traitement-typeTraitement'));
  planteSelect = element(by.css('select#traitement-plante'));
}
