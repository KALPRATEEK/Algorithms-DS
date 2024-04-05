% Provided data
graphSizes = [100, 500, 1000, 5000, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000];
executionTimes = [3, 0, 1, 3, 4, 10, 8, 5, 8, 14, 5, 6, 9, 12];

% Plot the data
figure;

% Plot execution times
subplot(2, 1, 1);
plot(graphSizes, executionTimes, '-o');
xlabel('Graph Size');
ylabel('Execution Time (milliseconds)');
title('Execution Time vs. Graph Size');
grid on;

% Plot (graph size)^2
subplot(2, 1, 2);
plot(graphSizes, graphSizes.^2, '-o', 'Color', 'r');
xlabel('Graph Size');
ylabel('(Graph Size)^2');
title('Graph Size vs. (Graph Size)^2');
grid on;

% Save the plot as an image
saveas(gcf, 'execution_time_and_size_comparison_updated.png');
