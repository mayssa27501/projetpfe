import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

export interface Locale {
  id: number;
  name: string;
}

export interface AssignLocaleDialogData {
  boxId: number;
  locales: Locale[];
  currentLocaleId: number | null;
}

@Component({
  selector: 'app-assign-locale-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatDialogModule
  ],
  template: `
    <h2 mat-dialog-title>Affecter un Locale à la Box</h2>
    <mat-dialog-content>
      <!-- Loading Spinner -->
      <div class="loading-spinner" *ngIf="isLoading">
        <mat-spinner diameter="40"></mat-spinner>
      </div>

      <!-- Error Message -->
      <div class="error-message" *ngIf="errorMessage && !isLoading">
        {{ errorMessage }}
      </div>

      <!-- Form -->
      <form [formGroup]="assignForm" class="space-y-1" *ngIf="!isLoading">
        <mat-form-field appearance="outline" class="w-full">
          <mat-label>Service</mat-label>
          <mat-select formControlName="localeId" required>
            <mat-option *ngFor="let locale of data.locales" [value]="locale.id">
              {{ locale.name }}
            </mat-option>
            <mat-option *ngIf="!data.locales.length" disabled>
              Aucun service disponible
            </mat-option>
          </mat-select>
          <mat-error *ngIf="assignForm.get('localeId')?.hasError('required')">
            Le service est requis
          </mat-error>
        </mat-form-field>
      </form>
    </mat-dialog-content>
    <mat-dialog-actions align="end" *ngIf="!isLoading">
      <button mat-stroked-button color="warn" (click)="onCancel()" class="debug-button">
        Annuler
      </button>
      <button mat-flat-button color="primary" [disabled]="!assignForm.valid" (click)="onSubmit()" class="debug-button">
        Affecter
      </button>
    </mat-dialog-actions>
  `,
  styles: [`
    :host {
      display: block;
      padding: 1rem;
    }
    mat-dialog-title {
      font-size: 1.5rem;
      font-weight: 600;
      color: #1f2937;
      margin-bottom: 1rem;
    }
    mat-dialog-content {
      padding: 1rem 0;
      min-height: 100px;
    }
    mat-dialog-actions {
      padding-top: 1rem;
      border-top: 1px solid #e5e7eb;
    }
    button[mat-flat-button],
    button[mat-stroked-button] {
      border-radius: 8px;
      padding: 0.5rem 1rem;
      font-weight: 500;
      transition: background-color 0.3s ease, transform 0.2s ease;
    }
    button[mat-flat-button].debug-button {
      background: #007bff !important;
      color: white !important;
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
    .loading-spinner {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 2rem;
    }
    .error-message {
      color: #d32f2f;
      font-size: 0.9rem;
      margin-bottom: 1rem;
      text-align: center;
    }
    mat-form-field {
      transition: all 0.3s ease;
    }
    mat-form-field:focus-within {
      transform: translateY(-2px);
    }
  `]
})
export class AssignLocaleDialogComponent {
  assignForm: FormGroup;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<AssignLocaleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AssignLocaleDialogData
  ) {
    console.log('AssignLocaleDialogComponent initialized with data:', data);
    this.assignForm = this.fb.group({
      localeId: [data.currentLocaleId || '', Validators.required]
    });

    // Validate initial data
    if (!data.locales || !Array.isArray(data.locales)) {
      this.errorMessage = 'Aucune donnée de locale fournie';
      this.isLoading = false;
      console.error('Invalid locales data:', data.locales);
    }
  }

  onSubmit(): void {
    if (this.assignForm.valid) {
      console.log('Submitting localeId:', this.assignForm.value.localeId);
      this.dialogRef.close({ localeId: this.assignForm.value.localeId });
    } else {
      console.warn('Form is invalid:', this.assignForm.errors);
      this.errorMessage = 'Veuillez sélectionner un locale';
    }
  }

  onCancel(): void {
    console.log('Dialog cancelled');
    this.dialogRef.close();
  }
}