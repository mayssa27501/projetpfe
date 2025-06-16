import { Component, OnInit, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AjouterOnduleurService } from './ajouter-onduleur.service';
import { GroupeOnduleurService } from '../ajouter-groupe-onduleur/groupe-onduleur.service';
import { GroupFormDialogComponent } from './group-form-dialog.component';

// Enum definition for ModeleOnduleur
enum ModeleOnduleur {
  UNO_DM_5_0 = 'UNO_DM_5_0',
}

// Enum definition for TypeOnduleur
enum TypeOnduleur {
  ONDULEUR_SOLAIRE = 'ONDULEUR_SOLAIRE',
}

@Component({
  selector: 'app-ajouter-onduleur',
  standalone: true,
  templateUrl: './ajouter-onduleur.component.html',
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
    .search-input-onduleur .search-field mat-form-field {
      width: 12rem;
    }
    .search-input-group mat-form-field {
      width: 100%;
    }
    .search-field .mat-form-field-outline {
      border-radius: 12px !important;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .search-field .mat-form-field-infix {
      padding: 0.5rem 0.75rem !important;
      font-size: 0.95rem !important;
    }
    .search-field input {
      padding: 0.25rem 0.5rem !important;
      color: #374151;
    }
    .search-field input::placeholder {
      color: #9ca3af;
      font-style: italic;
    }
    .search-field .mat-icon {
      color: #6b7280;
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
    button[mat-flat-button][color="accent"],
    button[mat-stroked-button][color="accent"],
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
    .group-cards {
      display: flex;
      flex-direction: column;
      gap: 1rem;
    }
    .group-card {
      background: #ffffff;
      border-radius: 12px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      overflow: hidden;
      transition: transform 0.2s ease, box-shadow 0.2s ease;
    }
    .group-card:hover {
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
    .card-actions {
      display: flex;
      justify-content: flex-end;
      padding: 0.5rem 1rem;
      background: #f9fafb;
    }
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
  `],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule
  ]
})
export class AjouterOnduleurComponent implements OnInit, AfterViewInit {
  onduleurForm: FormGroup;
  basicInfoForm: FormGroup;
  onduleurs: any[] = [];
  filteredOnduleurs: any[] = [];
  onduleurSearchTerm: string = '';
  sites: any[] = [];
  locales: any[] = [];
  boxes: any[] = [];
  groupeOnduleurs: any[] = [];
  filteredGroupeOnduleurs: any[] = [];
  groupSearchTerm: string = '';
  formVisible: boolean = false;
  showGroupDrawer: boolean = false;
  selectedOnduleurId: number | null = null;
  isEditMode: boolean = false;
  isLoading: boolean = false;
  isLoadingGroups: boolean = false;
  errorMessage: string = '';
  groupErrorMessage: string = '';
  showDetails: boolean = false;
  selectedOnduleurForDetails: any = null;
  selectedGroupeOnduleurForDetails: any = null;

  // Single option for ModeleOnduleur
  modeleOnduleurOptions: { value: ModeleOnduleur; label: string }[] = [
    { value: ModeleOnduleur.UNO_DM_5_0, label: 'UNO-DM-5.0' },
  ];

  // Single option for TypeOnduleur
  typeOnduleurOptions: { value: TypeOnduleur; label: string }[] = [
    { value: TypeOnduleur.ONDULEUR_SOLAIRE, label: 'Onduleur Solaire' },
  ];

  constructor(
    private fb: FormBuilder,
    private ajouterOnduleurService: AjouterOnduleurService,
    private groupeOnduleurService: GroupeOnduleurService,
    private cdr: ChangeDetectorRef,
    private dialog: MatDialog
  ) {
    this.onduleurForm = this.fb.group({
      code: ['', [Validators.required]],
      description: ['', [Validators.required]],
      siteId: [''],
      localeId: [''],
      boxId: [''],
      index: ['', [Validators.required, Validators.min(0)]],
      dateCreation: ['', [Validators.required]],
      heure: ['', [Validators.required, Validators.pattern(/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/)]],
      consommationKwh: ['', [Validators.required, Validators.min(0)]],
      productionKwh: ['', [Validators.required, Validators.min(0)]],
      adresse: ['', [Validators.pattern(/^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$/)]],
      court: [''],
      communication: ['', [Validators.required]],
      modeleOnduleur: [ModeleOnduleur.UNO_DM_5_0, [Validators.required]], // Default to UNO_DM_5_0
      type: [TypeOnduleur.ONDULEUR_SOLAIRE, [Validators.required]], // Default to ONDULEUR_SOLAIRE
      multiplicateur: ['', [Validators.required, Validators.min(1)]],
      bloque: [false]
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
    this.loadLocales();
    this.loadBoxes();
    this.loadGroupeOnduleurs();
  }

  ngAfterViewInit(): void {
    console.log('ngAfterViewInit called');
    setTimeout(() => {
      console.log('Forcing change detection after 500ms');
      const buttons = document.querySelectorAll('.debug-button');
      console.log('Found buttons:', buttons.length);
      this.cdr.detectChanges();
    }, 500);
  }

  loadOnduleurs(): void {
    this.isLoading = true;
    this.errorMessage = '';
    console.log('Loading onduleurs...');
    this.ajouterOnduleurService.getOnduleurs().subscribe({
      next: (data) => {
        this.onduleurs = data.map(onduleur => ({
          ...onduleur,
          site: {
            id: onduleur.siteId || onduleur.site?.id,
            name: onduleur.siteName || this.sites.find(s => s.id === (onduleur.siteId || onduleur.site?.id))?.name || 'N/A'
          },
          locale: {
            id: onduleur.localeId || onduleur.locale?.id,
            name: onduleur.localeName || this.locales.find(l => l.id === (onduleur.localeId || onduleur.locale?.id))?.name || 'N/A'
          },
          box: {
            id: onduleur.boxId || onduleur.box?.id,
            code: onduleur.boxCode || this.boxes.find(b => b.id === (onduleur.boxId || onduleur.box?.id))?.code || 'N/A'
          }
        }));
        this.filteredOnduleurs = [...this.onduleurs];
        console.log('Onduleurs loaded:', this.onduleurs);
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

  loadSites(): void {
    this.isLoading = true;
    console.log('Loading sites...');
    this.ajouterOnduleurService.getSites().subscribe({
      next: (data) => {
        this.sites = data;
        console.log('Sites loaded:', data);
        console.log('Sites count:', this.sites.length);
        this.loadOnduleurs();
      },
      error: (err) => {
        console.error('Error loading sites:', err);
        this.errorMessage = 'Échec du chargement des sites: ' + (err.message || 'Erreur inconnue');
        this.sites = [];
        this.isLoading = false;
        this.loadOnduleurs();
      }
    });
  }

  loadLocales(): void {
    console.log('Loading locales...');
    this.ajouterOnduleurService.getLocales().subscribe({
      next: (data) => {
        this.locales = data;
        console.log('Locales loaded:', data);
        console.log('Locales count:', this.locales.length);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading locales:', err);
        this.errorMessage = 'Échec du chargement des locales: ' + (err.message || 'Erreur inconnue');
        this.locales = [];
        this.cdr.detectChanges();
      }
    });
  }

  loadBoxes(): void {
    console.log('Loading boxes...');
    this.ajouterOnduleurService.getBoxes().subscribe({
      next: (data) => {
        this.boxes = data;
        console.log('Boxes loaded:', data);
        console.log('Boxes count:', this.boxes.length);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading boxes:', err);
        this.errorMessage = 'Échec du chargement des boxes: ' + (err.message || 'Erreur inconnue');
        this.boxes = [];
        this.cdr.detectChanges();
      }
    });
  }

  loadGroupeOnduleurs(): void {
    this.isLoadingGroups = true;
    this.groupErrorMessage = '';
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
        this.filteredGroupeOnduleurs = [...this.groupeOnduleurs];
        console.log('Groupe onduleurs loaded:', this.groupeOnduleurs);
        console.log('Groupe onduleurs count:', this.groupeOnduleurs.length);
        this.isLoadingGroups = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading groupe onduleurs:', err);
        this.groupErrorMessage = 'Échec du chargement des groupes onduleurs: ' + (err.message || 'Erreur inconnue');
        this.isLoadingGroups = false;
        this.cdr.detectChanges();
      }
    });
  }

  filterOnduleurs(): void {
    const searchTerm = this.onduleurSearchTerm.toLowerCase().trim();
    if (!searchTerm) {
      this.filteredOnduleurs = [...this.onduleurs];
    } else {
      this.filteredOnduleurs = this.onduleurs.filter(onduleur =>
        onduleur.code?.toLowerCase().includes(searchTerm) ||
        onduleur.description?.toLowerCase().includes(searchTerm)
      );
    }
    console.log('Filtered onduleurs:', this.filteredOnduleurs);
    this.cdr.detectChanges();
  }

  filterGroups(): void {
    const searchTerm = this.groupSearchTerm.toLowerCase().trim();
    if (!searchTerm) {
      this.filteredGroupeOnduleurs = [...this.groupeOnduleurs];
    } else {
      this.filteredGroupeOnduleurs = this.groupeOnduleurs.filter(groupe =>
        groupe.code?.toLowerCase().includes(searchTerm) ||
        groupe.description?.toLowerCase().includes(searchTerm)
      );
    }
    console.log('Filtered groupe onduleurs:', this.filteredGroupeOnduleurs);
    this.cdr.detectChanges();
  }

  toggleForm(): void {
    this.resetForms();
    this.formVisible = !this.formVisible;
    this.showGroupDrawer = false;
    console.log('Form visible:', this.formVisible);
    this.cdr.detectChanges();
  }

  toggleGroupDrawer(): void {
    this.showGroupDrawer = !this.showGroupDrawer;
    this.formVisible = false;
    this.showDetails = false;
    console.log('Group drawer visible:', this.showGroupDrawer);
    if (this.showGroupDrawer) {
      this.loadGroupeOnduleurs();
    }
    this.cdr.detectChanges();
  }

  closeAllPanels(): void {
    this.formVisible = false;
    this.showGroupDrawer = false;
    this.showDetails = false;
    this.resetForms();
    this.cdr.detectChanges();
  }

  openGroupFormDialog(groupe?: any): void {
    const dialogRef = this.dialog.open(GroupFormDialogComponent, {
      width: '600px',
      data: {
        groupe: groupe,
        sites: this.sites,
        onduleurs: this.onduleurs,
        isEditMode: !!groupe
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (groupe) {
          const index = this.groupeOnduleurs.findIndex(g => g.id === result.id);
          if (index !== -1) {
            this.groupeOnduleurs[index] = {
              ...result,
              site: {
                id: result.site?.id,
                name: this.sites.find(s => s.id === result.site?.id)?.name || 'N/A'
              }
            };
            this.filterGroups();
          }
        } else {
          this.groupeOnduleurs.push({
            ...result,
            site: {
              id: result.site?.id,
              name: this.sites.find(s => s.id === result.site?.id)?.name || 'N/A'
            }
          });
          this.filterGroups();
        }
        this.cdr.detectChanges();
      }
    });
  }

  editOnduleur(onduleur: any): void {
    console.log('Editing onduleur:', JSON.stringify(onduleur, null, 2));
    this.onduleurForm.patchValue({
      code: onduleur.code || '',
      description: onduleur.description || '',
      siteId: onduleur.site?.id || onduleur.siteId || '',
      localeId: onduleur.locale?.id || onduleur.localeId || '',
      boxId: onduleur.box?.id || onduleur.boxId || '',
      index: onduleur.index || '',
      dateCreation: onduleur.dateCreation || '',
      heure: onduleur.heure || '',
      consommationKwh: onduleur.consommationKwh || '',
      productionKwh: onduleur.productionKwh || '',
      adresse: onduleur.adresse || '',
      court: onduleur.court || '',
      communication: onduleur.communication || '',
      modeleOnduleur: onduleur.modeleOnduleur || ModeleOnduleur.UNO_DM_5_0, // Default to UNO_DM_5_0
      type: onduleur.type || TypeOnduleur.ONDULEUR_SOLAIRE, // Default to ONDULEUR_SOLAIRE
      multiplicateur: onduleur.multiplicateur || '',
      bloque: onduleur.bloque || false
    });
    this.basicInfoForm.patchValue({
      code: onduleur.code || '',
      description: onduleur.description || '',
      bloque: onduleur.bloque || false
    });
    this.selectedOnduleurId = onduleur.id;
    this.isEditMode = true;
    this.formVisible = true;
    this.showGroupDrawer = false;
    console.log('OnduleurForm validity after patch:', this.onduleurForm.valid);
    console.log('OnduleurForm values:', this.onduleurForm.value);
    this.cdr.detectChanges();
  }

  editGroup(groupe: any): void {
    this.openGroupFormDialog(groupe);
  }

  deleteOnduleur(id: number, event: MouseEvent): void {
    event.stopPropagation();
    console.log('Deleting onduleur:', id);
    this.ajouterOnduleurService.deleteOnduleur(id).subscribe({
      next: () => {
        this.onduleurs = this.onduleurs.filter(onduleur => onduleur.id !== id);
        this.filterOnduleurs();
        console.log('Onduleur deleted:', id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error deleting onduleur:', err);
        this.errorMessage = 'Échec de la suppression de l\'onduleur: ' + (err.message || 'Erreur inconnue');
        this.cdr.detectChanges();
      }
    });
  }

  deleteGroup(id: number, event: MouseEvent): void {
    event.stopPropagation();
    console.log('Deleting groupe onduleur:', id);
    this.groupeOnduleurService.deleteGroupeOnduleur(id).subscribe({
      next: () => {
        this.groupeOnduleurs = this.groupeOnduleurs.filter(groupe => groupe.id !== id);
        this.filterGroups();
        console.log('Groupe onduleur deleted:', id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error deleting groupe onduleur:', err);
        this.groupErrorMessage = 'Échec de la suppression du groupe onduleur: ' + (err.message || 'Erreur inconnue');
        this.cdr.detectChanges();
      }
    });
  }

  showOnduleurDetails(onduleur: any): void {
    console.log('Showing details for onduleur:', JSON.stringify(onduleur, null, 2));
    this.selectedOnduleurForDetails = onduleur;
    this.selectedGroupeOnduleurForDetails = null;
    this.showDetails = true;
    this.showGroupDrawer = false;
    this.cdr.detectChanges();
  }

  showGroupeOnduleurDetails(groupe: any): void {
    console.log('Showing details for groupe onduleur:', JSON.stringify(groupe, null, 2));
    this.selectedGroupeOnduleurForDetails = groupe;
    this.selectedOnduleurForDetails = null;
    this.showDetails = true;
    this.cdr.detectChanges();
  }

  closeDetails(): void {
    this.showDetails = false;
    this.selectedOnduleurForDetails = null;
    this.selectedGroupeOnduleurForDetails = null;
    this.cdr.detectChanges();
  }

  persistOnduleur(formType: 'onduleur' | 'basic'): void {
    let form: FormGroup;
    let formData: any;

    if (formType === 'onduleur') {
      form = this.onduleurForm;
      formData = {
        id: this.selectedOnduleurId,
        code: form.value.code,
        description: form.value.description,
        site: form.value.siteId ? { id: form.value.siteId } : null,
        locale: form.value.localeId ? { id: form.value.localeId } : null,
        box: form.value.boxId ? { id: form.value.boxId } : null,
        index: form.value.index,
        dateCreation: form.value.dateCreation,
        heure: form.value.heure,
        consommationKwh: form.value.consommationKwh,
        productionKwh: form.value.productionKwh,
        adresse: form.value.adresse,
        court: form.value.court,
        communication: form.value.communication,
        modeleOnduleur: form.value.modeleOnduleur, // Will be UNO_DM_5_0
        type: form.value.type, // Will be ONDULEUR_SOLAIRE
        multiplicateur: form.value.multiplicateur,
        bloque: form.value.bloque
      };
    } else {
      form = this.basicInfoForm;
      const onduleur = this.onduleurs.find(o => o.id === this.selectedOnduleurId);
      formData = {
        id: this.selectedOnduleurId,
        code: form.value.code,
        description: form.value.description,
        site: onduleur?.site || (this.onduleurForm.value.siteId ? { id: this.onduleurForm.value.siteId } : null),
        locale: onduleur?.locale || (this.onduleurForm.value.localeId ? { id: this.onduleurForm.value.localeId } : null),
        box: onduleur?.box || (this.onduleurForm.value.boxId ? { id: this.onduleurForm.value.boxId } : null),
        index: onduleur?.index || this.onduleurForm.value.index,
        dateCreation: onduleur?.dateCreation || this.onduleurForm.value.dateCreation,
        heure: onduleur?.heure || this.onduleurForm.value.heure,
        consommationKwh: onduleur?.consommationKwh || this.onduleurForm.value.consommationKwh,
        productionKwh: onduleur?.productionKwh || this.onduleurForm.value.productionKwh,
        adresse: onduleur?.adresse || this.onduleurForm.value.adresse,
        court: onduleur?.court || this.onduleurForm.value.court,
        communication: onduleur?.communication || this.onduleurForm.value.communication,
        modeleOnduleur: onduleur?.modeleOnduleur || this.onduleurForm.value.modeleOnduleur,
        type: onduleur?.type || this.onduleurForm.value.type,
        multiplicateur: onduleur?.multiplicateur || this.onduleurForm.value.multiplicateur,
        bloque: form.value.bloque
      };
    }

    if (form.valid) {
      console.log(`Submitting ${formType} form with data:`, JSON.stringify(formData, null, 2));
      this.errorMessage = '';
      this.isLoading = true;

      const selectedSite = this.sites.find(s => s.id === formData.site?.id);
      const selectedLocale = this.locales.find(l => l.id === formData.locale?.id);
      const selectedBox = this.boxes.find(b => b.id === formData.box?.id);

      if (this.isEditMode && this.selectedOnduleurId !== null) {
        console.log(`Sending PUT request to update onduleur ID ${this.selectedOnduleurId}`);
        this.ajouterOnduleurService.updateOnduleur(this.selectedOnduleurId, formData).subscribe({
          next: (updatedOnduleur) => {
            console.log('Onduleur updated successfully:', JSON.stringify(updatedOnduleur, null, 2));
            const index = this.onduleurs.findIndex(o => o.id === this.selectedOnduleurId);
            if (index !== -1) {
              this.onduleurs[index] = {
                ...updatedOnduleur,
                siteId: updatedOnduleur.site?.id || formData.site?.id,
                siteName: updatedOnduleur.site?.name || selectedSite?.name || this.onduleurs[index].siteName || 'N/A',
                localeId: updatedOnduleur.locale?.id || formData.locale?.id,
                localeName: updatedOnduleur.locale?.name || selectedLocale?.name || this.onduleurs[index].localeName || 'N/A',
                boxId: updatedOnduleur.box?.id || formData.box?.id,
                boxCode: updatedOnduleur.box?.code || selectedBox?.code || this.onduleurs[index].boxCode || 'N/A',
                site: {
                  id: updatedOnduleur.site?.id || formData.site?.id,
                  name: updatedOnduleur.site?.name || selectedSite?.name || this.onduleurs[index].siteName || 'N/A'
                },
                locale: {
                  id: updatedOnduleur.locale?.id || formData.locale?.id,
                  name: updatedOnduleur.locale?.name || selectedLocale?.name || this.onduleurs[index].localeName || 'N/A'
                },
                box: {
                  id: updatedOnduleur.box?.id || formData.box?.id,
                  code: updatedOnduleur.box?.code || selectedBox?.code || this.onduleurs[index].boxCode || 'N/A'
                }
              };
              this.filterOnduleurs();
              console.log('Updated onduleur in table:', JSON.stringify(this.onduleurs[index], null, 2));
            }
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error updating onduleur:', err);
            this.errorMessage = `Échec de la mise à jour de l'onduleur: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else if (formType === 'onduleur') {
        console.log('Sending POST request to add onduleur');
        this.ajouterOnduleurService.ajouterOnduleur(formData).subscribe({
          next: (newOnduleur) => {
            console.log('Onduleur added:', JSON.stringify(newOnduleur, null, 2));
            this.onduleurs.push({
              ...newOnduleur,
              siteId: newOnduleur.site?.id || formData.site?.id,
              siteName: newOnduleur.site?.name || selectedSite?.name || 'N/A',
              localeId: newOnduleur.locale?.id || formData.locale?.id,
              localeName: newOnduleur.locale?.name || selectedLocale?.name || 'N/A',
              boxId: newOnduleur.box?.id || formData.box?.id,
              boxCode: newOnduleur.box?.code || selectedBox?.code || 'N/A',
              site: {
                id: newOnduleur.site?.id || formData.site?.id,
                name: newOnduleur.site?.name || selectedSite?.name || 'N/A'
              },
              locale: {
                id: newOnduleur.locale?.id || formData.locale?.id,
                name: newOnduleur.locale?.name || selectedLocale?.name || 'N/A'
              },
              box: {
                id: newOnduleur.box?.id || formData.box?.id,
                code: newOnduleur.box?.code || selectedBox?.code || 'N/A'
              }
            });
            this.filterOnduleurs();
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error adding onduleur:', err);
            this.errorMessage = `Échec de l'ajout de l'onduleur: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
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
    this.onduleurForm.reset({
      code: '',
      description: '',
      siteId: '',
      localeId: '',
      boxId: '',
      index: '',
      dateCreation: '',
      heure: '',
      consommationKwh: '',
      productionKwh: '',
      adresse: '',
      court: '',
      communication: '',
      modeleOnduleur: ModeleOnduleur.UNO_DM_5_0, // Reset to default
      type: TypeOnduleur.ONDULEUR_SOLAIRE, // Reset to default
      multiplicateur: '',
      bloque: false
    });
    this.basicInfoForm.reset({
      code: '',
      description: '',
      bloque: false
    });
    this.formVisible = false;
    this.isEditMode = false;
    this.selectedOnduleurId = null;
    this.errorMessage = '';
    this.cdr.detectChanges();
  }

  logTabChange(event: any): void {
    console.log('Tab changed to index:', event.index);
    console.log('OnduleurForm validity:', this.onduleurForm.valid);
    console.log('OnduleurForm values:', this.onduleurForm.value);
  }
}