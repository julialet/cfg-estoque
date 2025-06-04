// Toast notification function
function showToast(message, type = 'add') {
    const toastContainer = document.getElementById('toastContainer');
    const toast = document.createElement('div');
    
    // Map the type to our custom classes
    let toastClass;
    switch(type) {
        case 'add':
            toastClass = 'toast-add';
            break;
        case 'remove':
            toastClass = 'toast-remove';
            break;
        case 'danger':
            toastClass = 'toast-danger';
            break;
        default:
            toastClass = 'toast-add';
    }
    
    toast.className = `toast ${toastClass} show`;
    toast.innerHTML = `
        <div class="toast-body">
            ${message}
        </div>
    `;
    toastContainer.appendChild(toast);
    
    // Remove toast after 3 seconds
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// Form handling with animation
function showAddForm() {
    const form = document.getElementById('addForm');
    if (form.style.display === 'none') {
        form.style.display = 'block';
        // Trigger reflow
        form.offsetHeight;
        form.classList.add('show');
    } else {
        form.classList.remove('show');
        setTimeout(() => {
            form.style.display = 'none';
        }, 300); // Match the CSS transition duration
    }
}

// Search/Filter functionality for equipamentos
function filterEquipamentos() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toLowerCase();
    const table = document.querySelector('table');
    const rows = table.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) { // Start from 1 to skip header
        const row = rows[i];
        if (row.classList.contains('empty-state')) continue;
        
        const cells = row.getElementsByTagName('td');
        let found = false;
        
        for (let j = 0; j < cells.length - 1; j++) { // Skip last cell (actions)
            const cell = cells[j];
            if (cell.textContent.toLowerCase().indexOf(filter) > -1) {
                found = true;
                break;
            }
        }
        
        row.style.display = found ? '' : 'none';
    }

    // Show/hide empty state
    const visibleRows = Array.from(rows).slice(1).filter(row => 
        row.style.display !== 'none' && !row.classList.contains('empty-state')
    );
    const emptyState = document.querySelector('.empty-state').parentElement;
    emptyState.style.display = visibleRows.length === 0 ? 'table-row' : 'none';
}

// Search/Filter functionality for usuarios
function filterUsers() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toLowerCase();
    const table = document.querySelector('table');
    const rows = table.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) { // Start from 1 to skip header
        const row = rows[i];
        if (row.classList.contains('empty-state')) continue;
        
        const cells = row.getElementsByTagName('td');
        let found = false;
        
        for (let j = 0; j < cells.length - 1; j++) { // Skip last cell (actions)
            const cell = cells[j];
            if (cell.textContent.toLowerCase().indexOf(filter) > -1) {
                found = true;
                break;
            }
        }
        
        row.style.display = found ? '' : 'none';
    }

    // Show/hide empty state
    const visibleRows = Array.from(rows).slice(1).filter(row => 
        row.style.display !== 'none' && !row.classList.contains('empty-state')
    );
    const emptyState = document.querySelector('.empty-state').parentElement;
    emptyState.style.display = visibleRows.length === 0 ? 'table-row' : 'none';
}

// Initialize toast messages if they exist
document.addEventListener('DOMContentLoaded', function() {
    const mensagem = document.getElementById('mensagem')?.value;
    const tipoMensagem = document.getElementById('tipoMensagem')?.value;
    if (mensagem) {
        showToast(mensagem, tipoMensagem);
    }
}); 