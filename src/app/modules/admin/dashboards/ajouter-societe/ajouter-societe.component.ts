
import { Component, OnInit, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { AjouterSocieteService } from './ajouter-societe.service';

@Component({
  selector: 'app-ajouter-societe',
  standalone: true,
  templateUrl: './ajouter-societe.component.html',
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
    .search-input-societe .search-field mat-form-field {
      width: 12rem;
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
    button[mat-flat-button][color="warn"],
    button[mat-stroked-button][color="warn"],
    button[mat-icon-button][color="warn"] {
      background: none !important;
      color: inherit !important;
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
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatDatepickerModule,
    MatNativeDateModule
  ]
})
export class AjouterSocieteComponent implements OnInit, AfterViewInit {
  societeForm: FormGroup;
  societes: any[] = [];
  filteredSocietes: any[] = [];
  societeSearchTerm: string = '';
  formVisible: boolean = false;
  selectedSocieteId: number | null = null;
  isEditMode: boolean = false;
  isLoading: boolean = false;
  errorMessage: string = '';
  showDetails: boolean = false;
  selectedSocieteForDetails: any = null;

  constructor(
    private fb: FormBuilder,
    private ajouterSocieteService: AjouterSocieteService,
    private cdr: ChangeDetectorRef
  ) {
    this.societeForm = this.fb.group({
      socialReason: ['', [Validators.required]],
      language: ['', [Validators.required]],
      timeZone: ['', [Validators.required]],
      license: ['', [Validators.required]],
      isBlocked: [false]
    });
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.loadSocietes();
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

  loadSocietes(): void {
    this.isLoading = true;
    this.errorMessage = '';
    console.log('Loading societes...');
    this.ajouterSocieteService.getSocietes().subscribe({
      next: (data) => {
        this.societes = data;
        this.filteredSocietes = [...this.societes];
        console.log('Societes loaded:', this.societes);
        console.log('Societes count:', this.societes.length);
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading societes:', err);
        this.errorMessage = 'Échec du chargement des sociétés: ' + (err.message || 'Erreur inconnue');
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  filterSocietes(): void {
    const searchTerm = this.societeSearchTerm.toLowerCase().trim();
    if (!searchTerm) {
      this.filteredSocietes = [...this.societes];
    } else {
      this.filteredSocietes = this.societes.filter(societe =>
        societe.socialReason?.toLowerCase().includes(searchTerm) ||
        societe.language?.toLowerCase().includes(searchTerm) ||
        societe.timeZone?.toLowerCase().includes(searchTerm)
      );
    }
    console.log('Filtered societes:', this.filteredSocietes);
    this.cdr.detectChanges();
  }

  toggleForm(): void {
    this.resetForm();
    this.formVisible = !this.formVisible;
    this.showDetails = false;
    console.log('Form visible:', this.formVisible);
    this.cdr.detectChanges();
  }

  closeAllPanels(): void {
    this.formVisible = false;
    this.showDetails = false;
    this.resetForm();
    this.cdr.detectChanges();
  }

  editSociete(societe: any): void {
    console.log('Editing societe:', JSON.stringify(societe, null, 2));
    this.societeForm.patchValue({
      socialReason: societe.socialReason || '',
      language: societe.language || '',
      timeZone: societe.timeZone || '',
      license: societe.license ? new Date(societe.license) : '',
      isBlocked: societe.isBlocked || false
    });
    this.selectedSocieteId = societe.id;
    this.isEditMode = true;
    this.formVisible = true;
    console.log('SocieteForm validity after patch:', this.societeForm.valid);
    console.log('SocieteForm values:', this.societeForm.value);
    this.cdr.detectChanges();
  }

  deleteSociete(id: number, event: MouseEvent): void {
    event.stopPropagation();
    console.log('Deleting societe:', id);
    this.ajouterSocieteService.deleteSociete(id).subscribe({
      next: () => {
        this.societes = this.societes.filter(societe => societe.id !== id);
        this.filteredSocietes = [...this.societes];
        console.log('Societe deleted:', id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error deleting societe:', err);
        this.errorMessage = 'Échec de la suppression de la société: ' + (err.message || 'Erreur inconnue');
        this.cdr.detectChanges();
      }
    });
  }

  showSocieteDetails(societe: any): void {
    console.log('Showing details for societe:', JSON.stringify(societe, null, 2));
    this.selectedSocieteForDetails = societe;
    this.showDetails = true;
    this.formVisible = false;
    this.cdr.detectChanges();
  }

  closeDetails(): void {
    this.showDetails = false;
    this.selectedSocieteForDetails = null;
    this.cdr.detectChanges();
  }

  persistSociete(): void {
    if (this.societeForm.valid) {
      const formData = {
        socialReason: this.societeForm.value.socialReason,
        language: this.societeForm.value.language,
        timeZone: this.societeForm.value.timeZone,
        license: this.societeForm.value.license.toISOString(),
        isBlocked: this.societeForm.value.isBlocked
      };
      console.log('Submitting societe form with data:', JSON.stringify(formData, null, 2));
      this.errorMessage = '';
      this.isLoading = true;

      if (this.isEditMode && this.selectedSocieteId !== null) {
        console.log(`Sending PUT request to update societe ID ${this.selectedSocieteId}`);
        this.ajouterSocieteService.updateSociete(this.selectedSocieteId, formData).subscribe({
          next: (updatedSociete) => {
            console.log('Societe updated successfully:', JSON.stringify(updatedSociete, null, 2));
            const index = this.societes.findIndex(s => s.id === this.selectedSocieteId);
            if (index !== -1) {
              this.societes[index] = updatedSociete;
              this.filteredSocietes = [...this.societes];
            }
            this.resetForm();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error updating societe:', err);
            this.errorMessage = `Échec de la mise à jour de la société: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else {
        console.log('Sending POST request to add societe');
        this.ajouterSocieteService.ajouterSociete(formData).subscribe({
          next: (newSociete) => {
            console.log('Societe added:', JSON.stringify(newSociete, null, 2));
            this.societes.push(newSociete);
            this.filteredSocietes = [...this.societes];
            this.resetForm();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error adding societe:', err);
            this.errorMessage = `Échec de l'ajout de la société: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      }
    } else {
      console.warn('Societe form invalid:', this.societeForm.errors);
      this.errorMessage = 'Le formulaire est invalide';
      this.isLoading = false;
      this.cdr.detectChanges();
    }
  }

  resetForm(): void {
    this.societeForm.reset({
      socialReason: '',
      language: '',
      timeZone: '',
      license: '',
      isBlocked: false
    });
    this.formVisible = false;
    this.isEditMode = false;
    this.selectedSocieteId = null;
    this.errorMessage = '';
    this.cdr.detectChanges();
  }
}
