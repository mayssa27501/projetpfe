import { Component, Inject, ChangeDetectorRef } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { GroupeOnduleurService } from '../ajouter-groupe-onduleur/groupe-onduleur.service';

@Component({
  selector: 'app-group-form-dialog',
  standalone: true,
  template: `
    <div class="dialog-content">
      <h2 mat-dialog-title class="text-2xl font-bold text-gray-800">
        {{ data.isEditMode ? 'Modifier un groupe onduleur' : 'Ajouter un groupe onduleur' }}
      </h2>
      <mat-dialog-content class="p-6">
        <!-- Error Message -->
        <div class="error-message" *ngIf="errorMessage">{{ errorMessage }}</div>

        <!-- Loading Spinner -->
        <div class="loading-spinner" *ngIf="isLoading">
          <mat-spinner diameter="50"></mat-spinner>
        </div>

        <form [formGroup]="groupeOnduleurForm" (ngSubmit)="onSubmit()" class="space-y-5" *ngIf="!isLoading">
          <mat-form-field appearance="outline">
            <mat-label>Code</mat-label>
            <input matInput type="text" formControlName="code" required />
            <mat-error *ngIf="groupeOnduleurForm.get('code')?.hasError('required')">
              Le code est requis
            </mat-error>
          </mat-form-field>

          <mat-form-field appearance="outline">
            <mat-label>Description</mat-label>
            <input matInput type="text" formControlName="description" required />
            <mat-error *ngIf="groupeOnduleurForm.get('description')?.hasError('required')">
              La description est requise
            </mat-error>
          </mat-form-field>

          <mat-form-field appearance="outline">
            <mat-label>Opération</mat-label>
            <input matInput type="text" formControlName="operation" />
          </mat-form-field>

          <mat-form-field appearance="outline">
            <mat-label>Site</mat-label>
            <mat-select formControlName="siteId">
              <mat-option *ngFor="let site of data.sites" [value]="site.id">
                {{ site.name }}
              </mat-option>
              <mat-option *ngIf="!data.sites.length" disabled>Aucun site disponible</mat-option>
            </mat-select>
          </mat-form-field>

          <mat-form-field appearance="outline">
            <mat-label>Onduleurs</mat-label>
            <mat-select formControlName="onduleurIds" multiple>
              <mat-option *ngFor="let onduleur of data.onduleurs" [value]="onduleur.id">
                {{ onduleur.code }}
              </mat-option>
              <mat-option *ngIf="!data.onduleurs.length" disabled>Aucun onduleur disponible</mat-option>
            </mat-select>
          </mat-form-field>

          <mat-checkbox formControlName="bloque">Bloqué</mat-checkbox>

          <div class="text-sm text-gray-500 mb-2">
            Sites chargés : {{ data.sites.length }} | Onduleurs chargés : {{ data.onduleurs.length }}
          </div>
        </form>
      </mat-dialog-content>
      <mat-dialog-actions class="flex justify-end gap-4 p-6">
        <button mat-stroked-button color="warn" class="debug-button" (click)="onCancel()">Annuler</button>
        <button mat-flat-button color="primary" class="debug-button" [disabled]="!groupeOnduleurForm.valid || isLoading" (click)="onSubmit()">
          {{ data.isEditMode ? 'Modifier' : 'Envoyer' }}
        </button>
      </mat-dialog-actions>
    </div>
  `,
  styles: [`
    .dialog-content {
      max-height: 80vh;
      overflow-y: auto;
    }
    .error-message {
      color: #d32f2f;
      font-size: 0.9rem;
      margin-bottom: 1rem;
    }
    .loading-spinner {
      display: flex;
      justify-content: center;
      padding: 2rem;
    }
    mat-form-field {
      width: 100%;
    }
    button[mat-flat-button].debug-button {
      background: #007bff !important;
      color: white !important;
    }
    button[mat-flat-button].debug-button:hover {
      background: #0056b3 !important;
    }
    button[mat-stroked-button].debug-button {
      background: #f1f5f9 !important;
      color: #4b5563 !important;
      border: 1px solid #d1d5db !important;
    }
    button[mat-stroked-button].debug-button:hover {
      background: #e5e7eb !important;
    }
  `],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatDialogModule // Add MatDialogModule here
  ]
})
export class GroupFormDialogComponent {
  groupeOnduleurForm: FormGroup;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    public dialogRef: MatDialogRef<GroupFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
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
      onduleurIds: [[]]
    });

    if (data.groupe) {
      this.groupeOnduleurForm.patchValue({
        code: data.groupe.code || '',
        description: data.groupe.description || '',
        operation: data.groupe.operation || '',
        siteId: data.groupe.site?.id || data.groupe.siteId || '',
        bloque: data.groupe.bloque || false,
        onduleurIds: data.groupe.onduleurs?.map((onduleur: any) => onduleur.id) || []
      });
    }
  }

  onSubmit(): void {
    if (this.groupeOnduleurForm.valid) {
      this.isLoading = true;
      this.errorMessage = '';
      const formData = {
        id: this.data.groupe?.id || null,
        code: this.groupeOnduleurForm.value.code,
        description: this.groupeOnduleurForm.value.description,
        operation: this.groupeOnduleurForm.value.operation,
        site: this.groupeOnduleurForm.value.siteId ? { id: this.groupeOnduleurForm.value.siteId } : null,
        bloque: this.groupeOnduleurForm.value.bloque,
        onduleurIds: this.groupeOnduleurForm.value.onduleurIds // Modified to match backend expectation
      };

      console.log('Submitting group form with data:', JSON.stringify(formData, null, 2));

      if (this.data.isEditMode && formData.id) {
        // Update existing group
        this.groupeOnduleurService.updateGroupeOnduleur(formData.id, formData).subscribe({
          next: (updatedGroup) => {
            console.log('Group updated successfully:', updatedGroup);
            this.isLoading = false;
            this.dialogRef.close(updatedGroup);
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error updating group:', err);
            this.errorMessage = 'Échec de la mise à jour du groupe: ' + (err.message || 'Erreur inconnue');
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      } else {
        // Add new group
        this.groupeOnduleurService.ajouterGroupeOnduleur(formData).subscribe({
          next: (newGroup) => {
            console.log('Group added successfully:', newGroup);
            this.isLoading = false;
            this.dialogRef.close(newGroup);
            this.cdr.detectChanges();
          },
          error: (err) => {
            console.error('Error adding group:', err);
            this.errorMessage = 'Échec de l\'ajout du groupe: ' + (err.message || 'Erreur inconnue');
            this.isLoading = false;
            this.cdr.detectChanges();
          }
        });
      }
    } else {
      console.warn('Form invalid:', this.groupeOnduleurForm.errors);
      this.errorMessage = 'Le formulaire est invalide';
      this.cdr.detectChanges();
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}