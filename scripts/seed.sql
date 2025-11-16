  ('alice@example.com','standard'),
  ('bob@example.com','premium');
INSERT INTO goals (user_id, name, target_amount)VALUES  (1,'Buy Phone',20000.00),
  (2,'Emergency Fund',50000.00);
INSERT INTO transactions (user_id, amount, type, occurred_at)VALUES  (1,5000.00,'CREDIT', NOW() - INTERVAL 40 DAY),
  (1,-1500.00,'DEBIT', NOW() - INTERVAL 10 DAY),
  (2,20000.00,'CREDIT', NOW() - INTERVAL 5 DAY),
  (2,-500.00,'DEBIT', NOW() - INTERVAL 2 DAY);