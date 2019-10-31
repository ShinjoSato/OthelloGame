# Othello

## あらすじ

メイン文は```FrameBase.java```とする．
```
$javac FrameBase.java
$java FrameBase
```

## 特徴
私はオセロが大好きである．
このプログラムは私が大学３年次前期から製作し始めたものである．

## 構成
画像（gif含める）は```./picture```に収められる．

## 今後の流れ
ますはじめに，コードの整理から始めたい．
今の状態でも最低限動作するが，動作が思いと思う．

## 追記(2019-10-04)
緑色のボード、石が表示されなくなってしまった。

原因は、
- import java.awt.*;
- import javax.swing.*;
この２つの可能性が考えられる。

## 検証(2019-10-31)

|ファイル名|コンパイル|メッセージ or メモ|
|:-|:-:|:-|
|Othello.java|○|またはJavaFXアプリケーション・クラスはjavafx.application.Applicationを拡張する必要があります|
|OthelloArt.java|○|またはJavaFXアプリケーション・クラスはjavafx.application.Applicationを拡張する必要があります|
|OthPanel.java|○|またはJavaFXアプリケーション・クラスはjavafx.application.Applicationを拡張する必要があります|
|PlayPanel.java|○|またはJavaFXアプリケーション・クラスはjavafx.application.Applicationを拡張する必要があります|
|SetPanel.java|○|またはJavaFXアプリケーション・クラスはjavafx.application.Applicationを拡張する必要があります|
|FrameBase.java|○|JFrameが作成され、プレイできる。しかし、<strong style="color: red;">画像が表示されない！！</strong>|
|HomePanel.java|○|またはJavaFXアプリケーション・クラスはjavafx.application.Applicationを拡張する必要があります|
|Main15.java|○|Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 61|
|OthConstant.java|○|またはJavaFXアプリケーション・クラスはjavafx.application.Applicationを拡張する必要があります|
|MainFrame.java|×|<strong style="color: red;">MainFrame.java:7: エラー: 無効なメソッド宣言です。戻り値の型が必要です。</strong>|