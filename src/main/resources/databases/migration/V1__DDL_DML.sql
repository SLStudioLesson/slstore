
-- 会員プランテーブル
CREATE TABLE membership_plans (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    monthly_fee INT NOT NULL,
    features TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 支払い方法テーブル
CREATE TABLE payment_methods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "users" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    membership_plan_id INT REFERENCES membership_plans(id),
    payment_method_id INT REFERENCES payment_methods(id),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    first_name_kana VARCHAR(50),
    last_name_kana VARCHAR(50),
    birth_date DATE,
    gender VARCHAR(10),
    phone_number VARCHAR(15),
    is_active BOOLEAN DEFAULT TRUE,
    last_login_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE admins (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    first_name_kana VARCHAR(50),
    last_name_kana VARCHAR(50),
    role_id VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 管理者データの挿入
INSERT INTO admins (username, email, password_hash, first_name, last_name, first_name_kana, last_name_kana, role_id)
VALUES 
('admin1', 'admin1@example.com', '$2a$10$wr/WSn.aUj0z./d9SpkVM.LrxiYmHjWhbyV0WCgvIO49IMzcO3x9y', '管理', '者一', 'カンリ', 'シャイチ', 'ROLE_SUPER_ADMIN'),
('admin2', 'admin2@example.com', '$2a$10$wr/WSn.aUj0z./d9SpkVM.LrxiYmHjWhbyV0WCgvIO49IMzcO3x9y', '管理', '者二', 'カンリ', 'シャニ', 'ROLE_ADMIN');

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    display_name VARCHAR(100) NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE admin_roles (
    admin_id INT REFERENCES admins(id),
    role_id INT REFERENCES roles(id),
    PRIMARY KEY (admin_id, role_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE prefectures (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    kana VARCHAR(50) NOT NULL,
    region VARCHAR(50)
);

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    address_type VARCHAR(10),
    postal_code CHAR(7) NOT NULL,
    prefecture_id INT REFERENCES prefectures(id),
    city VARCHAR(100) NOT NULL,
    town VARCHAR(100) NOT NULL,
    block VARCHAR(50) NOT NULL,
    building VARCHAR(100),
    is_default BOOLEAN DEFAULT FALSE,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 注文テーブル
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    order_number VARCHAR(50) UNIQUE NOT NULL,
    total_amount INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    payment_method_id INT REFERENCES payment_methods(id),
    shipping_address_id INT REFERENCES addresses(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ブランドテーブルの作成
CREATE TABLE brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    logo VARCHAR(255),
    is_deleted INTEGER DEFAULT 0,
    status INT DEFAULT 0,
    establish_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- カテゴリーテーブルの作成
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    parent_category_id INT REFERENCES categories(id),
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 商品テーブルの作成
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    sku VARCHAR(50) UNIQUE,
    brand_id INT REFERENCES brands(id),
    category_id INT REFERENCES categories(id),
    price INT NOT NULL,
    main_image_url VARCHAR(255) DEFAULT '/product-images/dummy/main_sample.png',
    is_deleted INTEGER DEFAULT 0,
    is_featured BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    published_at TIMESTAMP
);

-- 商品の在庫テーブル
CREATE TABLE product_stocks (
    id SERIAL PRIMARY KEY,
    product_id INT UNIQUE NOT NULL REFERENCES products(id),
    quantity INT NOT NULL DEFAULT 0,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 商品サブ画像テーブルの作成
CREATE TABLE product_sub_images (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id),
    image_url VARCHAR(255) NOT NULL,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 注文詳細テーブルの作成
CREATE TABLE order_details (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(id),
    product_id INT REFERENCES products(id),
    quantity INT NOT NULL,
    unit_price INT NOT NULL,
    subtotal INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO membership_plans (name, description, monthly_fee, features)
VALUES 
('ベーシックプラン', '基本的な機能を含む標準プラン', 0, '基本的な商品閲覧,標準配送'),
('プレミアムプラン', '追加機能を含む上級プラン', 1000, '全商品閲覧可能,優先配送,カスタマーサポート'),
('ゴールドプラン', '全機能が利用可能な最上級プラン', 2500, '全機能利用可能,VIP配送,24時間サポート,特別割引');

INSERT INTO payment_methods (name, description)
VALUES 
('現金', '代金引き換えで支払いが可能です。'),
('クレジットカード', 'VISA、MasterCard、JCBなどのクレジットカードでの支払いが可能です。'),
('電子マネー', '電子マネーを使用してオンラインで簡単に支払いができます。');


-- パスワード test = $2a$10$wr/WSn.aUj0z./d9SpkVM.LrxiYmHjWhbyV0WCgvIO49IMzcO3x9y
INSERT INTO users (username, membership_plan_id, payment_method_id, email, password_hash, first_name, last_name, first_name_kana, last_name_kana, birth_date, gender, phone_number)
VALUES 
('tanaka', 1, 2, 'tanaka@example.com', '$2a$10$wr/WSn.aUj0z./d9SpkVM.LrxiYmHjWhbyV0WCgvIO49IMzcO3x9y', '太郎', '田中', 'タロウ', 'タナカ', '1980-01-01', 'male', '090-1234-5678'),
('yoshida', 1, 2, 'yoshida@example.com', '$2a$10$wr/WSn.aUj0z./d9SpkVM.LrxiYmHjWhbyV0WCgvIO49IMzcO3x9y', '花子', '吉田', 'ハナコ', 'ヨシダ', '1990-02-02', 'female', '080-2345-6789'),
('kato', 2, 3, 'kato@example.com', '$2a$10$wr/WSn.aUj0z./d9SpkVM.LrxiYmHjWhbyV0WCgvIO49IMzcO3x9y', '一郎', '加藤', 'イチロウ', 'カトウ', '1985-03-03', 'male', '070-3456-7890'),
('inoue', 2, 3, 'inoue@example.com', '$2a$10$wr/WSn.aUj0z./d9SpkVM.LrxiYmHjWhbyV0WCgvIO49IMzcO3x9y', '次郎', '井上', 'ジロウ', 'イノウエ', '1995-04-04', 'male', '060-4567-8901');


INSERT INTO prefectures (name, kana, region)
VALUES 
('東京都', 'とうきょうと', '関東'),
('大阪府', 'おおさかふ', '近畿'),
('北海道', 'ほっかいどう', '北海道');

INSERT INTO addresses (user_id, address_type, postal_code, prefecture_id, city, town, block, building, is_default)
VALUES 
(1, '自宅', '1234567', 1, '千代田区', '千代田', 'X-Y-Z', 'SLマンション101', true),
(1, '職場', '2345678', 1, '港区', '六本木', 'A-B-C', 'ブリアンタワー3階', false),
(1, '実家', '3456789', 2, '大阪市北区', '梅田', 'P-Q-R', null, false),
(2, '自宅', '4567890', 3, '札幌市中央区', '北1条西', 'L-M-N', '雪降りすぎアパート201', true),
(2, '職場', '5678901', 1, '渋谷区', '恵比寿', 'D-E-F', 'なんかおしゃれビル9階', false),
(3, '自宅', '6789012', 1, '渋谷区', '千駄ヶ谷', 'G-H-I', null, true),
(4, '自宅', '7890123', 2, '大阪市北区', '中之島', 'J-K-L', 'なんでやねんマンション301', true);

-- ブランドデータの挿入
INSERT INTO brands (name, description, logo) VALUES
('自社ブランド', '当社のメインブランド', 'http://example.com/logos/own_brand.png'),
('パートナーブランドA', '高品質な商品を提供するパートナー', 'http://example.com/logos/partner_a.png');

-- カテゴリーデータの挿入
INSERT INTO categories (name, parent_category_id, description) VALUES
('衣類', NULL, '全ての衣類商品'),
('トップス', 1, 'シャツ、Tシャツなど'),
('ボトムス', 1, 'パンツ、スカートなど');

-- 商品データの挿入
INSERT INTO products (name, description, sku, brand_id, category_id, price, is_deleted, is_featured) VALUES
('シンプルTシャツ', '柔らかな肌触りの定番Tシャツ', 'SKU001', 1, 2, 2000, 0, FALSE),
('デザインジーンズ', 'スタイリッシュなデザインジーンズ', 'SKU002', 2, 3, 8000, 0, TRUE),
('ベーシックポロシャツ', '通気性の良い快適な着心地のポロシャツ', 'SKU003', 1, 2, 3500, 0, FALSE),
('カジュアルパンツ', '普段使いに最適なカジュアルパンツ', 'SKU004', 1, 3, 4500, 0, FALSE),
('プレミアムTシャツ', '高品質素材を使用したプレミアムTシャツ', 'SKU005', 2, 2, 4000, 0, TRUE),
('ストレッチパンツ', '動きやすいストレッチ素材のパンツ', 'SKU006', 2, 3, 6000, 0, FALSE),
('長袖Tシャツ', '秋冬に最適な長袖Tシャツ', 'SKU007', 1, 2, 2800, 0, FALSE),
('デニムショートパンツ', '夏に快適なデニムショートパンツ', 'SKU008', 2, 3, 5500, 0, TRUE),
('スポーツTシャツ', '吸汗速乾性能のあるスポーツTシャツ', 'SKU009', 1, 2, 3000, 0, FALSE),
('チノパン', 'ビジネスカジュアルに最適なチノパン', 'SKU010', 2, 3, 7000, 0, FALSE),
('プリントTシャツ', 'オリジナルデザインのプリントTシャツ', 'SKU011', 1, 2, 2500, 0, TRUE),
('カーゴパンツ', '多機能なポケット付きカーゴパンツ', 'SKU012', 1, 3, 5000, 0, FALSE),
('Vネックシャツ', 'スタイリッシュなVネックシャツ', 'SKU013', 2, 2, 3200, 0, FALSE),
('スキニージーンズ', 'スタイリッシュなスキニージーンズ', 'SKU014', 2, 3, 7500, 0, TRUE),
('ラグランTシャツ', 'カジュアルなラグランTシャツ', 'SKU015', 1, 2, 2600, 0, FALSE),
('ワイドパンツ', 'トレンド感のあるワイドパンツ', 'SKU016', 1, 3, 6500, 0, FALSE),
('ヘンリーネックシャツ', 'こだわりのヘンリーネックシャツ', 'SKU017', 2, 2, 3800, 0, TRUE),
('アンクルパンツ', '足首がすっきり見えるアンクルパンツ', 'SKU018', 2, 3, 6800, 0, FALSE),
('タンクトップ', '夏に快適なタンクトップ', 'SKU019', 1, 2, 1800, 0, FALSE),
('クロップドパンツ', 'トレンド感のあるクロップドパンツ', 'SKU020', 1, 3, 5800, 0, TRUE);

INSERT INTO product_stocks (product_id, quantity, is_deleted) VALUES
(1, 100, 0),  -- シンプルTシャツ
(2, 0, 0),  -- デザインジーンズ
(3, 200, 0),  -- ベーシックポロシャツ
(4, 120, 0),  -- カジュアルパンツ
(5, 80, 0),   -- プレミアムTシャツ
(6, 90, 0),   -- ストレッチパンツ
(7, 50, 0),   -- 長袖Tシャツ
(8, 60, 0),   -- デニムショートパンツ
(9, 30, 0),   -- スポーツTシャツ
(10, 40, 0),  -- チノパン
(11, 150, 0), -- プリントTシャツ
(12, 110, 0), -- カーゴパンツ
(13, 65, 0),  -- Vネックシャツ
(14, 75, 0),  -- スキニージーンズ
(15, 85, 0),  -- ラグランTシャツ
(16, 70, 0),  -- ワイドパンツ
(17, 95, 0),  -- ヘンリーネックシャツ
(18, 10, 0),  -- アンクルパンツ
(19, 20, 0),  -- タンクトップ
(20, 30, 0);  -- クロップドパンツ

INSERT INTO product_sub_images (product_id, image_url) VALUES
-- 商品1: シンプルTシャツ
(1, '/product-images/dummy/sub_sample.png'),
(1, '/product-images/dummy/sub_sample.png'),

-- 商品2: デザインジーンズ
(2, '/product-images/dummy/sub_sample.png'),
(2, '/product-images/dummy/sub_sample.png'),

-- 商品3: ベーシックポロシャツ
(3, '/product-images/dummy/sub_sample.png'),
(3, '/product-images/dummy/sub_sample.png'),

-- 商品4: カジュアルパンツ
(4, '/product-images/dummy/sub_sample.png'),
(4, '/product-images/dummy/sub_sample.png'),

-- 商品5: プレミアムTシャツ
(5, '/product-images/dummy/sub_sample.png'),
(5, '/product-images/dummy/sub_sample.png'),

-- 商品6: ストレッチパンツ
(6, '/product-images/dummy/sub_sample.png'),
(6, '/product-images/dummy/sub_sample.png'),

-- 商品7: 長袖Tシャツ
(7, '/product-images/dummy/sub_sample.png'),
(7, '/product-images/dummy/sub_sample.png'),

-- 商品8: デニムショートパンツ
(8, '/product-images/dummy/sub_sample.png'),
(8, '/product-images/dummy/sub_sample.png'),

-- 商品9: スポーツTシャツ
(9, '/product-images/dummy/sub_sample.png'),
(9, '/product-images/dummy/sub_sample.png'),

-- 商品10: チノパン
(10, '/product-images/dummy/sub_sample.png'),
(10, '/product-images/dummy/sub_sample.png'),

-- 商品11: プリントTシャツ
(11, '/product-images/dummy/sub_sample.png'),
(11, '/product-images/dummy/sub_sample.png'),

-- 商品12: カーゴパンツ
(12, '/product-images/dummy/sub_sample.png'),
(12, '/product-images/dummy/sub_sample.png'),

-- 商品13: Vネックシャツ
(13, '/product-images/dummy/sub_sample.png'),
(13, '/product-images/dummy/sub_sample.png'),

-- 商品14: スキニージーンズ
(14, '/product-images/dummy/sub_sample.png'),
(14, '/product-images/dummy/sub_sample.png'),

-- 商品15: ラグランTシャツ
(15, '/product-images/dummy/sub_sample.png'),
(15, '/product-images/dummy/sub_sample.png'),

-- 商品16: ワイドパンツ
(16, '/product-images/dummy/sub_sample.png'),
(16, '/product-images/dummy/sub_sample.png'),

-- 商品17: ヘンリーネックシャツ
(17, '/product-images/dummy/sub_sample.png'),
(17, '/product-images/dummy/sub_sample.png'),

-- 商品18: アンクルパンツ
(18, '/product-images/dummy/sub_sample.png'),
(18, '/product-images/dummy/sub_sample.png'),

-- 商品19: タンクトップ
(19, '/product-images/dummy/sub_sample.png'),
(19, '/product-images/dummy/sub_sample.png'),

-- 商品20: クロップドパンツ
(20, '/product-images/dummy/sub_sample.png'),
(20, '/product-images/dummy/sub_sample.png');

INSERT INTO orders (user_id, order_number, total_amount, status, payment_method_id, shipping_address_id)
VALUES 
(1, 'ORD-2023-001', 15000, '配送済み', 1, 1),
(1, 'ORD-2023-002', 8500, '処理中', 2, 1),
(1, 'ORD-2023-003', 22000, '支払い待ち', 3, 2),
(1, 'ORD-2023-004', 5000, '配送中', 1, 3),
(2, 'ORD-2023-005', 30000, '完了', 2, 5),
(3, 'ORD-2023-006', 12500, 'キャンセル', 1, 6),
(4, 'ORD-2023-007', 18000, '処理中', 3, 7);

-- テストデータの挿入
INSERT INTO order_details (order_id, product_id, quantity, unit_price, subtotal) VALUES
-- ORD-2023-001の詳細 (total: 15000)
(1, 1, 2, 2000, 4000),   -- シンプルTシャツ 2枚
(1, 2, 1, 8000, 8000),   -- デザインジーンズ 1本
(1, 3, 1, 3000, 3000),   -- ベーシックポロシャツ 1枚

-- ORD-2023-002の詳細 (total: 8500)
(2, 4, 1, 4500, 4500),   -- カジュアルパンツ 1本
(2, 5, 1, 4000, 4000),   -- プレミアムTシャツ 1枚

-- ORD-2023-003の詳細 (total: 22000)
(3, 6, 2, 6000, 12000),  -- ストレッチパンツ 2本
(3, 7, 2, 2800, 5600),   -- 長袖Tシャツ 2枚
(3, 8, 1, 4400, 4400),   -- デニムショートパンツ 1本

-- ORD-2023-004の詳細 (total: 5000)
(4, 9, 1, 3000, 3000),   -- スポーツTシャツ 1枚
(4, 10, 1, 2000, 2000),  -- シンプルTシャツ 1枚

-- ORD-2023-005の詳細 (total: 30000)
(5, 11, 2, 7500, 15000), -- プレミアムジーンズ 2本
(5, 12, 3, 5000, 15000), -- カーゴパンツ 3本

-- ORD-2023-006の詳細 (total: 12500)
(6, 13, 2, 3200, 6400),  -- Vネックシャツ 2枚
(6, 14, 1, 6100, 6100),  -- スキニージーンズ 1本

-- ORD-2023-007の詳細 (total: 18000)
(7, 15, 3, 2600, 7800),  -- ラグランTシャツ 3枚
(7, 16, 2, 5100, 10200); -- ワイドパンツ 2本

CREATE TABLE notices (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    is_deleted INTEGER DEFAULT 0,
    priority INT DEFAULT 0,
    status INT DEFAULT 0,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO notices (title, content, is_deleted, priority, status, end_date)
VALUES
    ('夏季セール開始のお知らせ', 
     '7月1日から夏季セールを開始します。全商品最大50%オフ！この機会をお見逃しなく。',
     0,
     10,
     0,
     '2099-12-31'),

    ('システムメンテナンスのお知らせ', 
     '7月15日午前2時から4時まで、システムメンテナンスを実施いたします。この間はサービスをご利用いただけません。ご不便をおかけして申し訳ございません。',
     0,
     20,
     0,
     '2099-12-31'),

    ('新商品入荷のお知らせ', 
     '人気ブランドの秋冬新作が入荷しました！最新のファッショントレンドをチェックしてください。',
     0,
     5,
     0,
     '2099-12-31'),

    ('ポイント2倍キャンペーン', 
     '8月5日から8月20日まで、全商品購入でポイントが通常の2倍になります！この機会にぜひお買い物をお楽しみください。',
     0,
     15,
     0,
     '2099-12-31'),

    ('お客様感謝デー開催', 
     '日頃の感謝を込めて、9月1日に特別セールを開催いたします。会員様限定で追加割引も！',
     0,
     10,
     0,
     '2099-12-31');

CREATE TABLE user_favorite_brands (
    id BIGSERIAL PRIMARY KEY,
    brand_id INTEGER,
    user_id BIGINT,
    UNIQUE(user_id, brand_id)
);

CREATE TABLE user_favorite_products (
    id BIGSERIAL PRIMARY KEY,
    product_id INTEGER,
    user_id BIGINT,
    UNIQUE(user_id, product_id)
);

INSERT INTO user_favorite_products (user_id, product_id) VALUES
-- ユーザー1のお気に入り
(1, 1),
(1, 5),
(1, 8),
(1, 10),

-- ユーザー2のお気に入り
(2, 1),
(2, 2),
(2, 3),

-- ユーザー3のお気に入り
(3, 5),
(3, 6),
(3, 7),
(3, 8),

-- ユーザー4のお気に入り
(4, 1),
(4, 4),
(4, 7),
(4, 10);

-- 在庫変更履歴テーブル
CREATE TABLE stock_change_logs (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL REFERENCES products(id),
    previous_quantity INT NOT NULL,
    changed_quantity INT NOT NULL CHECK (changed_quantity BETWEEN -100 AND 100),
    current_quantity INT NOT NULL,
    reason TEXT,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE shipping_companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    tracking_url_format VARCHAR(255),
    contact_phone VARCHAR(15),
    delivery_days_min INT NOT NULL,
    delivery_days_max INT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- キャンペーンテーブルの作成
CREATE TABLE campaigns (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- キャンペーン適用条件テーブルの作成
CREATE TABLE campaign_conditions (
    id SERIAL PRIMARY KEY,
    campaign_id INT UNIQUE REFERENCES campaigns(id),
    minimun_purchase INTEGER NOT NULL,
    valid_from DATE NOT NULL,
    valid_to DATE NOT NULL,
    discount_percentage INT NOT NULL CHECK (discount_percentage BETWEEN 1 AND 100),
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- キャンペーンデータの挿入
INSERT INTO campaigns (name, description) VALUES
('年末キャンペーン', '年末期間の全商品対象で割り引きされるキャンペーンです'),
('新生活応援キャンペーン', '新生活が始まるこの時期の全商品対象で割り引きされるキャンペーンです'),
('夏休みキャンペーン', '蒸し暑くなってきたこの時期の全商品対象で割り引きされるキャンペーンです');

-- キャンペーン適用条件データの挿入
INSERT INTO campaign_conditions (campaign_id, minimun_purchase, valid_from, valid_to, discount_percentage) VALUES
(1, 10000, '2024-12-01', '2024-12-31', 10),
(2, 20000, '2024-04-01', '2024-04-30', 20),
(3, 30000, '2024-08-01', '2024-08-31', 30);

CREATE TABLE faqs (
    id SERIAL PRIMARY KEY,
    category INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    published_at DATE NOT NULL,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO faqs (category, title, content, published_at) VALUES
-- カテゴリー1: 商品・配送について
(1, '返品・交換について', '商品到着後7日以内であれば、返品・交換が可能です。ただし、着用済みの商品や破損した商品は対象外となります。', '2025-01-01'),
(1, '配送料について', '1回のご注文が5,000円以上の場合、配送料は無料となります。5,000円未満の場合は、全国一律500円の配送料を頂戴しております。', '2025-01-01'),
(1, '在庫状況について', '商品ページに表示される在庫数は、リアルタイムの在庫を反映しています。「在庫切れ」の場合は入荷お知らせメールにご登録いただけます。', '2025-01-01'),

-- カテゴリー2: 会員登録・ログインについて
(2, '会員登録の方法', '画面右上の「新規会員登録」ボタンから、必要事項を入力して登録することができます。メールアドレスの認証が必要となります。', '2025-01-01'),
(2, 'パスワードを忘れた場合', 'ログイン画面の「パスワードをお忘れの方」から、登録メールアドレスを入力することでパスワードの再設定が可能です。', '2025-01-01'),
(2, '会員情報の変更方法', 'マイページの「会員情報編集」から、登録情報の変更が可能です。メールアドレスの変更には再認証が必要となります。', '2025-01-01'),

-- カテゴリー3: ポイント・クーポンについて
(3, 'ポイントの有効期限', '獲得したポイントの有効期限は、獲得日から1年間となります。期限切れ前にご利用ください。', '2025-01-01'),
(3, 'ポイントの付与タイミング', '商品発送後、7日以内にポイントが付与されます。キャンペーン期間中は付与までに14日程度かかる場合があります。', '2025-01-01'),
(3, 'クーポンの使用方法', 'お買い物かごの「クーポンコードを入力」欄に、発行されたコードを入力してください。複数のクーポンの併用はできません。', '2025-01-01');

CREATE TABLE promotion_codes (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    description TEXT,
    discount_type INT NOT NULL,
    discount_value INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO promotion_codes
(code, description, discount_type, discount_value, start_date, end_date) VALUES
('WELCOME10', '新規会員様向けの10%割引クーポンです。', 1, 10, '2024-01-01', '2026-12-31'),
('SUMMER500', '夏季キャンペーン500円割引クーポンです。', 2, 500, '2024-07-01', '2024-08-31'),
('WINTER2025', '2025年冬季限定20%割引クーポンです。', 1, 20, '2025-01-01', '2025-02-28');

CREATE TABLE affiliates (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE affiliates_details (
    id SERIAL PRIMARY KEY,
    affiliates_id INT REFERENCES affiliates(id),
    email VARCHAR(50) NOT NULL,
    referral_code VARCHAR(50) NOT NULL,
    commission_rate INT NOT NULL CHECK (commission_rate BETWEEN 1 AND 100),
    media INT NOT NULL CHECK (media BETWEEN 1 AND 4),
    contract_start_date DATE NOT NULL,
    contract_end_date DATE,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO affiliates (name) VALUES
('HEKAKIN_TV'),
('綿鍋尚実'),
('OolongMask');

INSERT INTO affiliates_details
(affiliates_id, email, referral_code, commission_rate, media, contract_start_date, contract_end_date) VALUES
(1, 'hekakin@example.com', 'HEKAKIN10', 10, 1, '2024-01-01', '2026-12-31'),
(2, 'watanabe@example.com', 'WATANABE15', 15, 2, '2024-01-01', '2026-12-31'),
(3, 'oolong@example.com', 'OOLONG20', 20, 3, '2024-01-01', '2026-12-31');

CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id),
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    is_deleted INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO reviews (product_id, rating, comment, is_deleted) VALUES
(1, 5, 'とてもよかったです。', 0),
(1, 4, 'まあまあでした。', 0),
(1, 3, '普通でした。', 0);