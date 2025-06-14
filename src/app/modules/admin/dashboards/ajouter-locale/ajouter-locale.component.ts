import { Component, OnInit, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AjouterLocaleService } from './ajouter-locale.service';

@Component({
  selector: 'app-ajouter-locale',
  standalone: true,
  templateUrl: './ajouter-locale.component.html',
  styles: [`
    :host {
      display: block;
      padding: 2rem;
      background: linear-gradient(135deg, #f5f7fa 0%, #e4e7eb 100%);
      min-height: 100vh;
    }
    .overlay {
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
      background: rgba(0, 0, 0, 0.5);
      backdrop-filter: blur(2px);
      z-index: 999;
      animation: fadeIn 0.3s ease-in;
    }
    @keyframes fadeIn {
      from { opacity: 0; }
      to { opacity: 1; }
    }
    .drawer {
      position: fixed;
      top: 0;
      right: 0;
      width: 420px;
      height: 100%;
      background: #ffffff;
      box-shadow: -4px 0 20px rgba(0, 0, 0, 0.15);
      transform: translateX(100%);
      transition: transform 0.4s cubic-bezier(0.4, 0, 0.2, 1);
      z-index: 1000;
      border-left: 1px solid #e5e7eb;
      overflow-y: auto;
    }
    .drawer.open {
      transform: translateX(0);
    }
    .drawer .p-6 {
      padding: 1.5rem;
      height: 100%;
      overflow-y: auto;
    }
    .card-container {
      position: relative;
      max-width: 1200px;
      margin: 0 auto;
    }
    .card {
      background: #ffffff;
      border-radius: 16px;
      padding: 2rem;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    .card:hover {
      transform: translateY(-4px);
      box-shadow: 0 6px 24px rgba(0, 0, 0, 0.12);
    }
    .styled-table {
      width: 100%;
      border-collapse: collapse;
      background: #ffffff;
      border-radius: 12px;
      overflow: hidden;
      font-size: 0.95rem;
    }
    .styled-table th,
    .styled-table td {
      padding: 1rem 1.25rem;
      text-align: left;
      border-bottom: 1px solid #e5e7eb;
    }
    .styled-table thead {
      background: linear-gradient(90deg, #f8fafc 0%, #e2e8f0 100%);
      color: #1f2937;
      font-weight: 600;
      text-transform: uppercase;
      font-size: 0.85rem;
    }
    .styled-table tbody tr {
      transition: background 0.2s ease;
    }
    .hover-row:hover {
      background: #f1f5f9;
      cursor: pointer;
    }
    mat-form-field {
      transition: all 0.3s ease;
      width: 100%;
    }
    mat-form-field:focus-within {
      transform: translateY(-2px);
    }
    button[mat-flat-button],
    button[mat-stroked-button] {
      border-radius: 8px;
      padding: 0.75rem 1.5rem;
      font-weight: 500;
      min-width: 120px;
      transition: background-color 0.3s ease, transform 0.2s ease;
    }
    button[mat-flat-button].debug-button {
      background: #007bff !important;
      color: white !important;
      border: none !important;
      box-shadow: 0 2px 8px rgba(0, 123, 255, 0.3);
    }
    button[mat-flat-button].debug-button:hover {
      background: #0056b3 !important;
      transform: translateY(-2px);
    }
    button[mat-flat-button].debug-button:disabled {
      background: #b0c4de !important;
      color: #ffffff !important;
      box-shadow: none;
    }
    button[mat-stroked-button].debug-button {
      background: #f1f5f9 !important;
      color: #4b5563 !important;
      border: 1px solid #d1d5db !important;
      box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
    }
    button[mat-stroked-button].debug-button:hover {
      background: #e5e7eb !important;
      transform: translateY(-1px);
    }
    button[mat-icon-button].debug-button {
      background: transparent !important;
      color: #ef4444 !important;
      border-radius: 50%;
      transition: background-color 0.3s ease;
    }
    button[mat-icon-button].debug-button.info-button {
      color: #3b82f6 !important;
    }
    button[mat-icon-button].debug-button:hover {
      background: #fee2e2 !important;
    }
    button[mat-icon-button].info-button:hover {
      background: #dbeafe !important;
    }
    button[mat-flat-button][color="primary"],
    button[mat-stroked-button][color="primary"],
    button[mat-flat-button][color="warn"],
    button[mat-stroked-button][color="warn"],
    button[mat-icon-button][color="warn"] {
      background: none !important;
      color: inherit !important;
    }
    mat-tab-group {
      margin-top: 1rem;
    }
    .tab-content {
      padding: 1rem;
      overflow-y: auto;
      max-height: calc(100vh - 150px);
    }
    .error-message {
      color: #d32f2f;
      font-size: 0.9rem;
      margin-top: 1rem;
    }
    .loading-spinner {
      display: flex;
      justify-content: center;
      padding: 2rem;
    }
    .details-panel {
      position: fixed;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background: #ffffff;
      padding: 2rem;
      border-radius: 16px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
      z-index: 1001;
      max-height: 80vh;
      overflow-y: auto;
      width: 500px;
    }
    .close-button {
      position: absolute;
      top: 1rem;
      right: 1rem;
      background: none;
      border: none;
      font-size: 1.5rem;
      cursor: pointer;
      color: #4b5563;
    }
    .close-button:hover {
      color: #ef4444;
    }
    @media (max-width: 768px) {
      .drawer {
        width: 100%;
        max-width: 100%;
      }
      .card {
        padding: 1rem;
      }
      .details-panel {
        width: 90%;
      }
    }
  `],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule,
    MatCheckboxModule,
    MatProgressSpinnerModule
  ]
})
export class AjouterLocaleComponent implements OnInit, AfterViewInit {
  localeForm: FormGroup;
  basicInfoForm: FormGroup;
  locales: any[] = [];
  sites: any[] = [];
  formVisible: boolean = false;
  selectedLocaleId: number | null = null;
  isEditMode: boolean = false;
  isLoading: boolean = false;
  errorMessage: string = '';
  showDetails: boolean = false;
  selectedLocaleForDetails: any = null;

  constructor(
    private fb: FormBuilder,
    private ajouterLocaleService: AjouterLocaleService,
    private cdr: ChangeDetectorRef
  ) {
    this.localeForm = this.fb.group({
      code: ['', [Validators.required]],
      name: ['', [Validators.required]],
      description: [''],
      siteId: ['', [Validators.required]],
      blocked: [false]
    });
    this.basicInfoForm = this.fb.group({
      code: ['', [Validators.required]],
      name: ['', [Validators.required]],
      blocked: [false]
    });
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.loadSites();
  }

  ngAfterViewInit(): void {
    console.log('ngAfterViewInit called');
    console.log('Form visible:', this.formVisible, 'Edit mode:', this.isEditMode);
    setTimeout(() => {
      console.log('Forcing change detection after 500ms');
      const buttons = document.querySelectorAll('.debug-button');
      console.log('Found buttons:', buttons.length);
      this.cdr.detectChanges();
    }, 500);
  }

  loadLocales(): void {
    this.isLoading = true;
    this.errorMessage = '';
    console.log('Loading locales...');
    this.ajouterLocaleService.getLocales().subscribe({
      next: (data) => {
        this.locales = data.map(locale => ({
          ...locale,
          site: {
            id: locale.siteId || locale.site?.id,
            description: locale.siteDescription || this.sites.find(s => s.id === (locale.siteId || locale.site?.id))?.description || 'N/A'
          }
        }));
        console.log('Locales loaded:', this.locales);
        console.log('Locales count:', this.locales.length);
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading locales:', err);
        this.errorMessage = 'Échec du chargement des locales: ' + (err.message || 'Erreur inconnue');
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  loadSites(): void {
    this.isLoading = true;
    console.log('Loading sites...');
    this.ajouterLocaleService.getSites().subscribe({
      next: (data) => {
        this.sites = data;
        console.log('Sites loaded:', data);
        console.log('Sites count:', this.sites.length);
        this.loadLocales();
      },
      error: (err) => {
        console.error('Error loading sites:', err);
        this.errorMessage = 'Échec du chargement des sites: ' + (err.message || 'Erreur inconnue');
        this.sites = [];
        this.isLoading = false;
        this.loadLocales();
      }
    });
  }

  toggleForm(): void {
    this.resetForms();
    this.formVisible = !this.formVisible;
    console.log('Form visible:', this.formVisible);
    this.cdr.detectChanges();
  }

  editLocale(locale: any): void {
    console.log('Editing locale:', JSON.stringify(locale, null, 2));
    this.localeForm.patchValue({
      code: locale.code || '',
      name: locale.name || '',
      description: locale.description || '',
      siteId: locale.site?.id || locale.siteId || '',
      blocked: locale.blocked || false
    });
    this.basicInfoForm.patchValue({
      code: locale.code || '',
      name: locale.name || '',
      blocked: locale.blocked || false
    });
    this.selectedLocaleId = locale.id;
    this.isEditMode = true;
    this.formVisible = true;
    console.log('LocaleForm validity after patch:', this.localeForm.valid);
    console.log('LocaleForm values:', this.localeForm.value);
    this.cdr.detectChanges();
  }

  deleteLocale(id: number, event: MouseEvent): void {
    event.stopPropagation();
    console.log('Deleting locale:', id);
    this.ajouterLocaleService.deleteLocale(id).subscribe({
      next: () => {
        this.locales = this.locales.filter(locale => locale.id !== id);
        console.log('Locale deleted:', id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error deleting locale:', err);
        this.errorMessage = 'Échec de la suppression du local: ' + (err.message || 'Erreur inconnue');
        this.cdr.detectChanges();
      }
    });
  }

  showLocaleDetails(locale: any): void {
    console.log('Showing details for locale:', JSON.stringify(locale, null, 2));
    this.selectedLocaleForDetails = locale;
    this.showDetails = true;
    this.cdr.detectChanges();
  }

  closeDetails(): void {
    this.showDetails = false;
    this.selectedLocaleForDetails = null;
    this.cdr.detectChanges();
  }

  persistLocale(formType: 'locale' | 'basic'): void {
    let form: FormGroup;
    let formData: any;

    if (formType === 'locale') {
      form = this.localeForm;
      formData = {
        id: this.selectedLocaleId,
        code: form.value.code,
        name: form.value.name,
        description: form.value.description,
        site: form.value.siteId ? { id: form.value.siteId } : null,
        blocked: form.value.blocked
      };
    } else {
      form = this.basicInfoForm;
      const locale = this.locales.find(l => l.id === this.selectedLocaleId);
      formData = {
        id: this.selectedLocaleId,
        code: form.value.code,
        name: form.value.name,
        description: locale?.description || this.localeForm.value.description,
        site: locale?.site || (this.localeForm.value.siteId ? { id: this.localeForm.value.siteId } : null),
        blocked: form.value.blocked
      };
    }

    if (form.valid) {
      console.log(`Submitting ${formType} form with data:`, JSON.stringify(formData, null, 2));
      this.errorMessage = '';
      this.isLoading = true;

      const selectedSite = this.sites.find(s => s.id === formData.site?.id);

      if (this.isEditMode && this.selectedLocaleId !== null) {
        console.log(`Sending PUT request to update locale ID ${this.selectedLocaleId}`);
        this.ajouterLocaleService.updateLocale(this.selectedLocaleId, formData).subscribe({
          next: (updatedLocale) => {
            console.log('Locale updated successfully:', JSON.stringify(updatedLocale, null, 2));
            const index = this.locales.findIndex(l => l.id === this.selectedLocaleId);
            if (index !== -1) {
              this.locales[index] = {
                ...updatedLocale,
                siteId: updatedLocale.site?.id || formData.site?.id,
                siteDescription: updatedLocale.site?.description || selectedSite?.description || this.locales[index].siteDescription || 'N/A',
                site: {
                  id: updatedLocale.site?.id || formData.site?.id,
                  description: updatedLocale.site?.description || selectedSite?.description || this.locales[index].siteDescription || 'N/A'
                }
              };
              console.log('Updated locale in table:', JSON.stringify(this.locales[index], null, 2));
            }
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error updating locale:', err);
            this.errorMessage = `Échec de la mise à jour du local: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else if (formType === 'locale') {
        console.log('Sending POST request to add locale');
        this.ajouterLocaleService.ajouterLocale(formData).subscribe({
          next: (newLocale) => {
            console.log('Locale added:', JSON.stringify(newLocale, null, 2));
            this.locales.push({
              ...newLocale,
              siteId: newLocale.site?.id || formData.site?.id,
              siteDescription: newLocale.site?.description || selectedSite?.description || 'N/A',
              site: {
                id: newLocale.site?.id || formData.site?.id,
                description: newLocale.site?.description || selectedSite?.description || 'N/A'
              }
            });
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error adding locale:', err);
            this.errorMessage = `Échec de l'ajout du local: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else {
        this.errorMessage = 'L’ajout est uniquement autorisé via le formulaire d’informations générales';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    } else {
      console.warn(`Form ${formType} invalid:`, form.errors);
      this.errorMessage = `Le formulaire ${formType} est invalide`;
      this.isLoading = false;
      this.cdr.detectChanges();
    }
  }

  resetForms(): void {
    this.localeForm.reset({
      code: '',
      name: '',
      description: '',
      siteId: '',
      blocked: false
    });
    this.basicInfoForm.reset({
      code: '',
      name: '',
      blocked: false
    });
    this.formVisible = false;
    this.isEditMode = false;
    this.selectedLocaleId = null;
    this.errorMessage = '';
    this.cdr.detectChanges();
  }

  logTabChange(event: any): void {
    console.log('Tab changed to index:', event.index);
    console.log('LocaleForm validity:', this.localeForm.valid);
    console.log('LocaleForm values:', this.localeForm.value);
  }
}