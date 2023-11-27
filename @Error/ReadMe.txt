【DO378用のsettings.xmlについて】　※AVDの場合
 mvn package -DskipTests -s "C:\Users\enami\.m2\settings-DO378-Quarkus.xml"


＜mvn package実行時のエラーについて＞
・未検証
・あくまで推測
だが、エラー発生の原因は
・テスト用のコンテナを起動しておく必要がある
・デプロイ用のOpenShiftを準備し、設定しておく必要がある
などに起因しているものと考えられる。