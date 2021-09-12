import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class PlanteUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('santeplantesApp.plante.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomScientifiqueInput: ElementFinder = element(by.css('input#plante-nomScientifique'));

  nomCommunInput: ElementFinder = element(by.css('input#plante-nomCommun'));

  familleInput: ElementFinder = element(by.css('input#plante-famille'));

  genreInput: ElementFinder = element(by.css('input#plante-genre'));

  photoInput: ElementFinder = element(by.css('input#file_photo'));

  bienfaitsInput: ElementFinder = element(by.css('input#plante-bienfaits'));

  imageBienfaitsInput: ElementFinder = element(by.css('input#file_imageBienfaits'));

  typeMaladiesInput: ElementFinder = element(by.css('input#plante-typeMaladies'));

  maladiesInput: ElementFinder = element(by.css('input#plante-maladies'));
}
