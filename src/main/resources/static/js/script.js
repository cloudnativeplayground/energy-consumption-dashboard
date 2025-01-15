// This function will be used to fetch and display energy usage data in real-time.
function fetchEnergyUsage() {
    $.get("/api/energy-usage", function(data) {
        if (data) {
            const energyUsageHtml = `
                <div class="row">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Current Power Usage (kWh)</h5>
                                <p class="card-text">${data.currentUsage} kWh</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Total Power Usage (kWh)</h5>
                                <p class="card-text">${data.totalUsage} kWh</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Devices Active</h5>
                                <p class="card-text">${data.activeDevices}</p>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            $("#energyUsageContainer").html(energyUsageHtml);
        } else {
            $("#energyUsageContainer").html('<p>No energy usage data available.</p>');
        }
    }).fail(function() {
        alert("Error fetching energy usage data.");
    });
}

// This function fetches and displays active alerts from the backend.
function fetchAlerts() {
    $.get("/api/alerts", function(data) {
        const alertListContainer = $("#alertList");

        if (data.length > 0) {
            const alertsHtml = data.map(alert => {
                return `
                    <div class="alert alert-${alert.type}" role="alert">
                        <h4 class="alert-heading">${alert.title}</h4>
                        <p>${alert.message}</p>
                        <hr>
                        <p class="mb-0"><strong>Timestamp:</strong> ${new Date(alert.timestamp).toLocaleString()}</p>
                    </div>
                `;
            }).join('');
            alertListContainer.html(alertsHtml);
        } else {
            alertListContainer.html('<p>No active alerts at the moment.</p>');
        }
    }).fail(function() {
        alert("Error fetching alerts.");
    });
}

// This function resolves the current alert when the "Resolve" button is clicked.
$("#resolveAlertBtn").click(function() {
    $.post("/api/alerts/resolve", function(response) {
        if (response.success) {
            alert("Alert resolved successfully.");
            fetchAlerts();  // Refresh the alert list
        } else {
            alert("Error resolving alert. Please try again.");
        }
    }).fail(function() {
        alert("Error resolving alert. Please try again.");
    });
});

// Function to fetch and display energy usage data periodically (every 30 seconds)
function startRealTimeEnergyMonitoring() {
    setInterval(fetchEnergyUsage, 30000);  // Fetch energy usage every 30 seconds
}

// Function to show the current usage data in a table (example)
function displayEnergyUsageData() {
    $.get("/api/energy-usage", function(data) {
        if (data) {
            const tableHtml = `
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Device</th>
                            <th>Usage (kWh)</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${data.deviceUsage.map(device => {
                            return `
                                <tr>
                                    <td>${device.name}</td>
                                    <td>${device.usage}</td>
                                    <td>${device.status}</td>
                                </tr>
                            `;
                        }).join('')}
                    </tbody>
                </table>
            `;
            $("#energyUsageTable").html(tableHtml);
        } else {
            $("#energyUsageTable").html('<p>No data available.</p>');
        }
    }).fail(function() {
        alert("Error fetching energy usage data.");
    });
}

// Function to display a success notification
function showSuccessNotification(message) {
    const notificationHtml = `
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <strong>Success!</strong> ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    $("#notifications").append(notificationHtml);
}

// Function to display an error notification
function showErrorNotification(message) {
    const notificationHtml = `
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong>Error!</strong> ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    $("#notifications").append(notificationHtml);
}

// Initialize the dashboard by fetching data and starting real-time monitoring
$(document).ready(function() {
    fetchEnergyUsage();
    fetchAlerts();
    startRealTimeEnergyMonitoring();
    displayEnergyUsageData();
});

