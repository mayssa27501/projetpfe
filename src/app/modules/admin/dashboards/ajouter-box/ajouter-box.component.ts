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
import { AjouterBoxService } from './ajouter-box.service';

@Component({
  selector: 'app-ajouter-box',
  standalone: true,
  templateUrl: './ajouter-box.component.html',
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
export class AjouterBoxComponent implements OnInit, AfterViewInit {
  boxForm: FormGroup;
  basicInfoForm: FormGroup;
  hardwareConfigForm: FormGroup;
  boxes: any[] = [];
  modeles: any[] = [];
  formVisible: boolean = false;
  selectedBoxId: number | null = null;
  isEditMode: boolean = false;
  isLoading: boolean = false;
  errorMessage: string = '';
  showDetails: boolean = false;
  selectedBoxForDetails: any = null;
  modeleAttributesKeys: string[] = [];

  constructor(
    private fb: FormBuilder,
    private ajouterBoxService: AjouterBoxService,
    private cdr: ChangeDetectorRef
  ) {
    console.log('AjouterBoxComponent initialized');
    this.boxForm = this.fb.group({
      code: ['', Validators.required],
      description: ['', Validators.required],
      serialNumber: ['', Validators.required],
      modeleId: ['', Validators.required],
      isBlocked: [false]
    });
    this.basicInfoForm = this.fb.group({
      code: ['', Validators.required],
      description: ['', Validators.required],
      isBlocked: [false]
    });
    this.hardwareConfigForm = this.fb.group({
      code: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.loadModeles();
    this.boxForm.get('modeleId')?.valueChanges.subscribe((modeleId) => {
      if (this.isEditMode && modeleId) {
        console.log('Modele ID changed:', modeleId);
        this.loadModeleAttributes(modeleId);
      }
    });
  }

  ngAfterViewInit(): void {
    console.log('ngAfterViewInit called');
    console.log('Form visible:', this.formVisible, 'Edit mode:', this.isEditMode);
    setTimeout(() => {
      console.log('Forcing change detection after 500ms');
      const buttons = document.querySelectorAll('.debug-button');
      console.log('Found buttons:', buttons.length);
      const hardwareSubmitButton = document.querySelector('button[type="submit"][class*="debug-button"]:not([disabled])');
      console.log('Hardware config submit button found:', !!hardwareSubmitButton);
      this.cdr.detectChanges();
    }, 500);
  }

  loadBoxes(): void {
    this.isLoading = true;
    this.errorMessage = '';
    console.log('Loading boxes...');
    this.ajouterBoxService.getBoxes().subscribe({
      next: (data) => {
        this.boxes = data.map(box => ({
          ...box,
          modele: {
            id: box.modeleId || box.modele?.id,
            name: box.modeleName || this.modeles.find(m => m.id === (box.modeleId || box.modele?.id))?.name || 'N/A',
            attributes: box.modeleAttributes || {}
          },
          modeleAttributes: box.modeleAttributes || {}
        }));
        console.log('Boxes loaded:', this.boxes);
        console.log('Boxes count:', this.boxes.length);
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading boxes:', err);
        this.errorMessage = 'Échec du chargement des boxes: ' + (err.message || 'Erreur inconnue');
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  loadModeles(): void {
    this.isLoading = true;
    console.log('Loading modeles...');
    this.ajouterBoxService.getModeles().subscribe({
      next: (data) => {
        this.modeles = data;
        console.log('Modeles loaded:', data);
        console.log('Modeles count:', this.modeles.length);
        this.loadBoxes();
      },
      error: (err) => {
        console.error('Error loading modeles:', err);
        this.errorMessage = 'Échec du chargement des modèles: ' + (err.message || 'Erreur inconnue');
        this.modeles = [];
        this.isLoading = false;
        this.loadBoxes();
      }
    });
  }

  loadModeleAttributes(modeleId: string): void {
    console.log('Loading attributes for modeleId:', modeleId);
    this.isLoading = true;
    this.ajouterBoxService.getModele(modeleId).subscribe({
      next: (modele) => {
        console.log('Modele attributes loaded:', modele.attributes);
        this.modeleAttributesKeys = Object.keys(modele.attributes || {});
        this.hardwareConfigForm = this.fb.group({
          code: [this.hardwareConfigForm.get('code')?.value || '', Validators.required]
        });
        this.modeleAttributesKeys.forEach(key => {
          const box = this.boxes.find(b => b.id === this.selectedBoxId);
          const value = box?.modeleAttributes[key] || modele.attributes[key] || '';
          this.hardwareConfigForm.addControl(key, this.fb.control(value));
        });
        console.log('HardwareConfigForm updated:', this.hardwareConfigForm.value);
        console.log('HardwareConfigForm valid:', this.hardwareConfigForm.valid);
        this.isLoading = false;
        this.errorMessage = '';
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading modele attributes:', err);
        this.errorMessage = `Échec du chargement des attributs du modèle: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
        const box = this.boxes.find(b => b.id === this.selectedBoxId);
        if (box && box.modeleAttributes) {
          console.log('Falling back to box attributes:', box.modeleAttributes);
          this.modeleAttributesKeys = Object.keys(box.modeleAttributes);
          this.hardwareConfigForm = this.fb.group({
            code: [this.hardwareConfigForm.get('code')?.value || '', Validators.required]
          });
          this.modeleAttributesKeys.forEach(key => {
            this.hardwareConfigForm.addControl(key, this.fb.control(box.modeleAttributes[key] || ''));
          });
        } else {
          console.log('No box attributes available, resetting form');
          this.modeleAttributesKeys = [];
          this.hardwareConfigForm = this.fb.group({
            code: ['', Validators.required]
          });
        }
        console.log('HardwareConfigForm after error:', this.hardwareConfigForm.value);
        console.log('HardwareConfigForm valid:', this.hardwareConfigForm.valid);
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

  editBox(box: any): void {
    console.log('Editing box:', JSON.stringify(box, null, 2));
    this.boxForm.patchValue({
      code: box.code || '',
      description: box.description || '',
      serialNumber: box.serialNumber || '',
      modeleId: box.modele?.id || box.modeleId || '',
      isBlocked: box.isBlocked || false
    });
    this.basicInfoForm.patchValue({
      code: box.code || '',
      description: box.description || '',
      isBlocked: box.isBlocked || false
    });
    this.hardwareConfigForm = this.fb.group({
      code: [box.code || '', Validators.required]
    });
    this.modeleAttributesKeys = Object.keys(box.modeleAttributes || {});
    this.modeleAttributesKeys.forEach(key => {
      this.hardwareConfigForm.addControl(key, this.fb.control(box.modeleAttributes[key] || ''));
    });
    this.selectedBoxId = box.id;
    this.isEditMode = true;
    this.formVisible = true;
    console.log('HardwareConfigForm validity after patch:', this.hardwareConfigForm.valid);
    console.log('HardwareConfigForm values:', this.hardwareConfigForm.value);
    this.cdr.detectChanges();
    if (box.modele?.id || box.modeleId) {
      this.loadModeleAttributes((box.modele?.id || box.modeleId).toString());
    }
  }

  deleteBox(id: number, event: MouseEvent): void {
    event.stopPropagation();
    console.log('Deleting box:', id);
    this.ajouterBoxService.deleteBox(id).subscribe({
      next: () => {
        this.boxes = this.boxes.filter(box => box.id !== id);
        console.log('Box deleted:', id);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error deleting box:', err);
        this.errorMessage = 'Échec de la suppression de la box: ' + (err.message || 'Erreur inconnue');
        this.cdr.detectChanges();
      }
    });
  }

  showBoxDetails(box: any): void {
    console.log('Showing details for box:', JSON.stringify(box, null, 2));
    this.selectedBoxForDetails = box;
    this.modeleAttributesKeys = Object.keys(box.modeleAttributes || {});
    this.showDetails = true;
    this.cdr.detectChanges();
  }

  closeDetails(): void {
    this.showDetails = false;
    this.selectedBoxForDetails = null;
    this.modeleAttributesKeys = [];
    this.cdr.detectChanges();
  }

  persistBox(formType: 'box' | 'basic' | 'hardware'): void {
    let form: FormGroup;
    let formData: any;

    if (formType === 'box') {
      form = this.boxForm;
      const attributes: { [key: string]: string } = {};
      this.modeleAttributesKeys.forEach(key => {
        attributes[key] = this.hardwareConfigForm.get(key)?.value || '';
      });
      formData = {
        id: this.selectedBoxId,
        code: form.value.code,
        description: form.value.description,
        serialNumber: form.value.serialNumber,
        modele: { id: form.value.modeleId },
        isBlocked: form.value.isBlocked,
        modeleAttributes: attributes
      };
    } else if (formType === 'basic') {
      form = this.basicInfoForm;
      const attributes: { [key: string]: string } = {};
      this.modeleAttributesKeys.forEach(key => {
        attributes[key] = this.hardwareConfigForm.get(key)?.value || '';
      });
      formData = {
        id: this.selectedBoxId,
        code: form.value.code,
        description: form.value.description,
        serialNumber: this.boxForm.value.serialNumber || '',
        modele: { id: this.boxForm.value.modeleId || '' },
        isBlocked: form.value.isBlocked,
        modeleAttributes: attributes
      };
    } else {
      form = this.hardwareConfigForm;
      const attributes: { [key: string]: string } = {};
      this.modeleAttributesKeys.forEach(key => {
        attributes[key] = form.value[key] || '';
      });
      const box = this.boxes.find(b => b.id === this.selectedBoxId);
      formData = {
        id: this.selectedBoxId,
        code: form.value.code,
        description: box?.description || '',
        serialNumber: box?.serialNumber || '',
        modele: { id: box?.modele?.id || box?.modeleId || '' },
        isBlocked: box?.isBlocked || false,
        modeleAttributes: attributes
      };
    }

    if (form.valid) {
      console.log(`Submitting ${formType} form with data:`, JSON.stringify(formData, null, 2));
      this.errorMessage = '';
      this.isLoading = true;

      const selectedModele = this.modeles.find(m => m.id === formData.modele?.id);

      if (this.isEditMode && this.selectedBoxId !== null) {
        console.log(`Sending PUT request to update box ID ${this.selectedBoxId}`);
        this.ajouterBoxService.updateBox(this.selectedBoxId, formData).subscribe({
          next: (updatedBox) => {
            console.log('Box updated successfully:', JSON.stringify(updatedBox, null, 2));
            const index = this.boxes.findIndex(b => b.id === this.selectedBoxId);
            if (index !== -1) {
              const attributes = updatedBox.modeleAttributes || formData.modeleAttributes || {};
              this.boxes[index] = {
                ...updatedBox,
                modeleId: updatedBox.modele?.id || formData.modele.id,
                modeleName: updatedBox.modele?.name || selectedModele?.name || this.boxes[index].modeleName || 'N/A',
                modeleAttributes: attributes,
                modele: {
                  id: updatedBox.modele?.id || formData.modele.id,
                  name: updatedBox.modele?.name || selectedModele?.name || this.boxes[index].modeleName || 'N/A',
                  attributes: attributes
                }
              };
              console.log('Updated box in table:', JSON.stringify(this.boxes[index], null, 2));
            }
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error updating box:', err);
            this.errorMessage = `Échec de la mise à jour de la box: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else if (formType === 'box') {
        console.log('Sending POST request to add box');
        this.ajouterBoxService.ajouterBox(formData).subscribe({
          next: (newBox) => {
            console.log('Box added:', JSON.stringify(newBox, null, 2));
            const attributes = newBox.modeleAttributes || formData.modeleAttributes || {};
            this.boxes.push({
              ...newBox,
              modeleId: newBox.modele?.id || formData.modele.id,
              modeleName: newBox.modele?.name || selectedModele?.name || 'N/A',
              modeleAttributes: attributes,
              modele: {
                id: newBox.modele?.id || formData.modele.id,
                name: newBox.modele?.name || selectedModele?.name || 'N/A',
                attributes: attributes
              }
            });
            this.resetForms();
            this.isLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error adding box:', err);
            this.errorMessage = `Échec de l'ajout de la box: ${err.status ? `HTTP ${err.status} - ` : ''}${err.message || 'Erreur inconnue'}`;
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
    this.boxForm.reset({
      code: '',
      description: '',
      serialNumber: '',
      modeleId: '',
      isBlocked: false
    });
    this.basicInfoForm.reset({
      code: '',
      description: '',
      isBlocked: false
    });
    this.hardwareConfigForm = this.fb.group({
      code: ['', Validators.required]
    });
    this.modeleAttributesKeys = [];
    this.formVisible = false;
    this.isEditMode = false;
    this.selectedBoxId = null;
    this.errorMessage = '';
    this.cdr.detectChanges();
  }

  logTabChange(event: any): void {
    console.log('Tab changed to index:', event.index);
    console.log('HardwareConfigForm validity:', this.hardwareConfigForm.valid);
    console.log('HardwareConfigForm values:', this.hardwareConfigForm.value);
  }
}