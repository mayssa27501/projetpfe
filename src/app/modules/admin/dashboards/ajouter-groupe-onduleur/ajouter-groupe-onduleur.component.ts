
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
import { GroupeOnduleurService } from './groupe-onduleur.service';

@Component({
  selector: 'app-ajouter-groupe-onduleur',
  standalone: true,
  templateUrl: './ajouter-groupe-onduleur.component.html',
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
    /* Styles pour les cartes des onduleurs */
    .onduleur-cards {
      display: flex;
      flex-direction: column;
      gap: 1rem;
    }
    .onduleur-card {
      background: #ffffff;
      border-radius: 12px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      overflow: hidden;
      transition: transform 0.2s ease, box-shadow 0.2s ease;
    }
    .onduleur-card:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    }
    .card-header {
      background: linear-gradient(90deg, #f8fafc 0%, #e2e8f0 100%);
      padding: 0.75rem 1rem;
      border-bottom: 1px solid #e5e7eb;
    }
    .card-title {
      font-weight: 600;
      color: #1f2937;
      font-size: 0.95rem;
      text-transform: uppercase;
    }
    .card-body {
      padding: 1rem;
      font-size: 0.9rem;
      color: #4b5563;
    }
    .card-body p {
      margin: 0.5rem 0;
    }
    .card-body strong {
      color: #1f2937;
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
export class AjouterGroupeOnduleurComponent implements OnInit, AfterViewInit {
  groupeOnduleurForm: FormGroup;
  basicInfoForm: FormGroup;
  groupeOnduleurs: any[] = [];
  sites: any[] = [];
  onduleurs: any[] = []; // Liste des onduleurs
  formVisible: boolean = false;
  selectedGroupeOnduleurId: number | null = null;
  isEditMode: boolean = false;
  isLoading: boolean = false;
  errorMessage: string = '';
  showDetails: boolean = false;
  selectedGroupeOnduleurForDetails: any = null;

  constructor(
    private fb: FormBuilder,
    private groupeOnduleurService: GroupeOnduleurService,
    private cdr: ChangeDetectorRef
  ) {
    this.groupeOnduleurForm = this.fb.group({
      code: ['', [Validators.required]],
      description: ['', [Validators.required]],
      operation: [''],
      siteId: [''],
      bloque: [false],
      onduleurIds: [[]] // Contrôle pour les IDs des onduleurs
    });
    this.basicInfoForm = this.fb.group({
      code: ['', [Validators.required]],
      description: ['', [Validators.required]],
      bloque: [false]
    });
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.loadSites();
    this.loadOnduleurs();
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

  loadGroupeOnduleurs(): void {
    this.isLoading = true;
    this.errorMessage = '';
    console.log('Loading groupe onduleurs...');
    this.groupeOnduleurService.getGroupeOnduleurs().subscribe({
      next: (data) => {
        this.groupeOnduleurs = data.map(groupe => ({
          ...groupe,
          site: {
            id: groupe.siteId || groupe.site?.id,
            name: groupe.siteName || this.sites.find(s => s.id === (groupe.siteId || groupe.site?.id))?.name || 'N/A'
          }
        }));
        console.log('Groupe onduleurs loaded:', this.groupeOnduleurs);
        console.log('Groupe onduleurs count:', this.groupeOnduleurs.length);
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading groupe onduleurs:', err);
        this.errorMessage = 'Échec du chargement des groupes onduleurs: ' + (err.message || 'Erreur inconnue');
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  loadSites(): void {
    this.isLoading = true;
    console.log('Loading sites...');
    this.groupeOnduleurService.getSites().subscribe({
      next: (data) => {
        this.sites = data;
        console.log('Sites loaded:', data);
        console.log('Sites count:', this.sites.length);
        this.loadGroupeOnduleurs();
      },
      error: (err) => {
        console.error('Error loading sites:', err);
        this.errorMessage = 'Échec du chargement des sites: ' + (err.message || 'Erreur inconnue');
        this.sites = [];
        this.isLoading = false;
        this.loadGroupeOnduleurs();
      }
    });
  }

  loadOnduleurs(): void {
    this.isLoading = true;
    console.log('Loading onduleurs...');
    this.groupeOnduleurService.getOnduleurs().subscribe({
      next: (data) => {
        this.onduleurs = data;
        console.log('Onduleurs loaded:', data);
        console.log('Onduleurs count:', this.onduleurs.length);
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading onduleurs:', err);
        this.errorMessage = 'Échec du chargement des onduleurs: ' + (err.message || 'Erreur inconnue');
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  toggleForm(): void {
    this.resetForms();
    this.formVisible = !this.formVisible;
    console.log('Form visible:', this.formVisible);
    this.cdr.detectChanges();
  }

  editGroupeOnduleur(groupe: any): void {
    console.log('Editing groupe onduleur:', JSON.stringify(groupe, null, 2));
    this.groupeOnduleurForm.patchValue({
      code: groupe.code || '',
      description: groupe.description || '',
      operation: groupe.operation || '',
      siteId: groupe.site?.id || groupe.siteId || '',
      bloque: groupe.bloque || false,
      onduleurIds: groupe.onduleurs?.map((onduleur: any) => onduleur.id) || []
    });
    this.basicInfoForm.patchValue({
      code: groupe.code || '',
      description: groupe.description || '',
      bloque: groupe.bloque || false
    });
    this.selectedGroupeOnduleurId = groupe.id;
    this.isEditMode = true;
    this.formVisible = true;
    console.log('GroupeOnduleurForm validity after patch:', this.groupeOnduleurForm.valid);
    console.log('GroupeOnduleurForm values:', this.groupeOnduleurForm.value);
    this.cdr.detectChanges();
  }

  deleteGroupeOnduleur(id: number, event: MouseEvent): void {
    event.stopPropagation();
    console.log('Deleting groupe onduleur:', id);
    this.groupeOnduleurService.deleteGroupeOnduleur(id).subscribe({
      next: () => {
        this.groupeOnduleurs = this.groupeOnduleurs.filter(groupe => groupe.id !== id);
        console.log('Groupe onduleur deleted:', id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error deleting groupe onduleur:', err);
        this.errorMessage = 'Échec de la suppression du groupe onduleur: ' + (err.message || 'Erreur inconnue');
        this.cdr.detectChanges();
      }
    });
  }

  showGroupeOnduleurDetails(groupe: any): void {
    console.log('Showing details for groupe onduleur:', JSON.stringify(groupe, null, 2));
    this.selectedGroupeOnduleurForDetails = groupe;
    this.showDetails = true;
    this.cdr.detectChanges();
  }

  closeDetails(): void {
    this.showDetails = false;
    this.selectedGroupeOnduleurForDetails = null;
    this.cdr.detectChanges();
  }

  persistGroupeOnduleur(formType: 'groupe' | 'basic'): void {
    let form: FormGroup;
    let formData: any;

    if (formType === 'groupe') {
      form = this.groupeOnduleurForm;
      formData = {
        id: this.selectedGroupeOnduleurId,
        code: form.value.code,
        description: form.value.description,
        operation: form.value.operation,
        site: form.value.siteId ? { id: form.value.siteId } : null,
        bloque: form.value.bloque,
        onduleurIds: form.value.onduleurIds || []
      };
    } else {
      form = this.basicInfoForm;
      const groupe = this.groupeOnduleurs.find(g => g.id === this.selectedGroupeOnduleurId);
      formData = {
        id: this.selectedGroupeOnduleurId,
        code: form.value.code,
        description: form.value.description,
        operation: groupe?.operation || this.groupeOnduleurForm.value.operation,
        site: groupe?.site || (this.groupeOnduleurForm.value.siteId ? { id: this.groupeOnduleurForm.value.siteId } : null),
        bloque: form.value.bloque,
        onduleurIds: this.groupeOnduleurForm.value.onduleurIds || []
      };
    }

    if (form.valid) {
      console.log(`Submitting ${formType} form with data:`, JSON.stringify(formData, null, 2));
      this.errorMessage = '';
      this.isLoading = true;

      const selectedSite = this.sites.find(s => s.id === formData.site?.id);

      if (this.isEditMode && this.selectedGroupeOnduleurId !== null) {
        console.log(`Sending PUT request to update groupe onduleur ID ${this.selectedGroupeOnduleurId}`);
        this.groupeOnduleurService.updateGroupeOnduleur(this.selectedGroupeOnduleurId, formData).subscribe({
          next: (updatedGroupe) => {
            console.log('Groupe onduleur updated successfully:', JSON.stringify(updatedGroupe, null, 2));
            const index = this.groupeOnduleurs.findIndex(g => g.id === this.selectedGroupeOnduleurId);
            if (index !== -1) {
              this.groupeOnduleurs[index] = {
                ...updatedGroupe,
                siteId: updatedGroupe.site?.id || formData.site?.id,
                siteName: updatedGroupe.site?.name || selectedSite?.name || this.groupeOnduleurs[index].siteName || 'N/A',
                site: {
                  id: updatedGroupe.site?.id || formData.site?.id,
                  name: updatedGroupe.site?.name || selectedSite?.name || this.groupeOnduleurs[index].siteName || 'N/A'
                },
                onduleurs: updatedGroupe.onduleurs || []
              };
              console.log('Updated groupe onduleur in table:', JSON.stringify(this.groupeOnduleurs[index], null, 2));
            }
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error updating groupe onduleur:', err);
            this.errorMessage = `Échec de la mise à jour du groupe onduleur: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else if (formType === 'groupe') {
        console.log('Sending POST request to add groupe onduleur');
        this.groupeOnduleurService.ajouterGroupeOnduleur(formData).subscribe({
          next: (newGroupe) => {
            console.log('Groupe onduleur added:', JSON.stringify(newGroupe, null, 2));
            this.groupeOnduleurs.push({
              ...newGroupe,
              siteId: newGroupe.site?.id || formData.site?.id,
              siteName: newGroupe.site?.name || selectedSite?.name || 'N/A',
              site: {
                id: newGroupe.site?.id || formData.site?.id,
                name: newGroupe.site?.name || selectedSite?.name || 'N/A'
              },
              onduleurs: newGroupe.onduleurs || []
            });
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error adding groupe onduleur:', err);
            this.errorMessage = `Échec de l'ajout du groupe onduleur: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
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
    this.groupeOnduleurForm.reset({
      code: '',
      description: '',
      operation: '',
      siteId: '',
      bloque: false,
      onduleurIds: []
    });
    this.basicInfoForm.reset({
      code: '',
      description: '',
      bloque: false
    });
    this.formVisible = false;
    this.isEditMode = false;
    this.selectedGroupeOnduleurId = null;
    this.errorMessage = '';
    this.cdr.detectChanges();
  }

  logTabChange(event: any): void {
    console.log('Tab changed to index:', event.index);
    console.log('GroupeOnduleurForm validity:', this.groupeOnduleurForm.valid);
    console.log('GroupeOnduleurForm values:', this.groupeOnduleurForm.value);
  }
}