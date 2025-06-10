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
import { AjouterSiteService } from './ajouter-site.service';

@Component({
  selector: 'app-ajouter-site',
  standalone: true,
  templateUrl: './ajouter-site.component.html',
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
    }
    .drawer.open {
      transform: translateX(0);
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
      box-shadow: 0 6px 24px rgb(0, 0, 0, 0.12);
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
export class AjouterSiteComponent implements OnInit, AfterViewInit {
  siteForm: FormGroup;
  basicInfoForm: FormGroup;
  sites: any[] = [];
  formVisible: boolean = false;
  selectedSiteId: number | null = null;
  isEditMode: boolean = false;
  isLoading: boolean = false;
  errorMessage: string = '';
  showDetails: boolean = false;
  selectedSiteForDetails: any = null;

  constructor(
    private fb: FormBuilder,
    private ajouterSiteService: AjouterSiteService,
    private cdr: ChangeDetectorRef
  ) {
    this.siteForm = this.fb.group({
      description: ['', Validators.required],
      address: ['', Validators.required],
      timeZone: ['', Validators.required],
      positionGps: ['', Validators.required],
      isBlocked: [false],
      isPrincipal: [false]
    });
    this.basicInfoForm = this.fb.group({
      description: ['', Validators.required],
      address: ['', Validators.required],
      isBlocked: [false],
      isPrincipal: [false]
    });
  }

  ngOnInit(): void {
    this.loadSites();
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.cdr.detectChanges();
    }, 500);
  }

  loadSites(): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.ajouterSiteService.getSites().subscribe({
      next: (data) => {
        this.sites = data.map(site => ({
          ...site
        }));
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.errorMessage = 'Échec du chargement des sites: ' + (err.message || 'Erreur inconnue');
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  toggleForm(): void {
    this.resetForms();
    this.formVisible = !this.formVisible;
    this.cdr.detectChanges();
  }

  editSite(site: any): void {
    this.siteForm.patchValue({
      description: site.description || '',
      address: site.address || '',
      timeZone: site.timeZone || '',
      positionGps: site.positionGps || '',
      isBlocked: site.isBlocked || false,
      isPrincipal: site.isPrincipal || false
    });
    this.basicInfoForm.patchValue({
      description: site.description || '',
      address: site.address || '',
      isBlocked: site.isBlocked || false,
      isPrincipal: site.isPrincipal || false
    });
    this.selectedSiteId = site.id;
    this.isEditMode = true;
    this.formVisible = true;
    this.cdr.detectChanges();
  }

  deleteSite(id: number, event: MouseEvent): void {
    event.stopPropagation();
    this.ajouterSiteService.deleteSite(id).subscribe({
      next: () => {
        this.sites = this.sites.filter(site => site.id !== id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.errorMessage = 'Échec de la suppression du site: ' + (err.message || 'Erreur inconnue');
        this.cdr.detectChanges();
      }
    });
  }

  showSiteDetails(site: any): void {
    this.selectedSiteForDetails = site;
    this.showDetails = true;
    this.cdr.detectChanges();
  }

  closeDetails(): void {
    this.showDetails = false;
    this.selectedSiteForDetails = null;
    this.cdr.detectChanges();
  }

  persistSite(formType: 'site' | 'basic'): void {
    let form: FormGroup;
    let formData: any;

    if (formType === 'site') {
      form = this.siteForm;
      formData = {
        id: this.selectedSiteId,
        description: form.value.description,
        address: form.value.address,
        timeZone: form.value.timeZone,
        positionGps: form.value.positionGps,
        isBlocked: form.value.isBlocked,
        isPrincipal: form.value.isPrincipal
      };
    } else {
      form = this.basicInfoForm;
      formData = {
        id: this.selectedSiteId,
        description: form.value.description,
        address: form.value.address,
        timeZone: this.siteForm.value.timeZone || '',
        positionGps: this.siteForm.value.positionGps || '',
        isBlocked: form.value.isBlocked,
        isPrincipal: form.value.isPrincipal
      };
    }

    if (form.valid) {
      this.errorMessage = '';
      this.isLoading = true;

      if (this.isEditMode && this.selectedSiteId !== null) {
        this.ajouterSiteService.updateSite(this.selectedSiteId, formData).subscribe({
          next: (updatedSite) => {
            const index = this.sites.findIndex(s => s.id === this.selectedSiteId);
            if (index !== -1) {
              this.sites[index] = { ...updatedSite };
            }
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            this.errorMessage = `Échec de la mise à jour du site: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else if (formType === 'site') {
        this.ajouterSiteService.ajouterSite(formData).subscribe({
          next: (newSite) => {
            this.sites.push({ ...newSite });
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            this.errorMessage = `Échec de l'ajout du site: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
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
      this.errorMessage = `Le formulaire ${formType} est invalide`;
      this.isLoading = false;
      this.cdr.detectChanges();
    }
  }

  resetForms(): void {
    this.siteForm.reset({
      description: '',
      address: '',
      timeZone: '',
      positionGps: '',
      isBlocked: false,
      isPrincipal: false
    });
    this.basicInfoForm.reset({
      description: '',
      address: '',
      isBlocked: false,
      isPrincipal: false
    });
    this.formVisible = false;
    this.isEditMode = false;
    this.selectedSiteId = null;
    this.errorMessage = '';
    this.cdr.detectChanges();
  }

  logTabChange(event: any): void {
    console.log('Tab changed to index:', event.index);
  }
}